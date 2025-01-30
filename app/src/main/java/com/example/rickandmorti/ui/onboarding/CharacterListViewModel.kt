package com.example.rickandmorti.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorti.data.CartoonApiService
import com.example.rickandmorti.data.models.BaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val apiService: CartoonApiService,
    private val database: CharacterDao
) : ViewModel() {

    private val _characterList = MutableLiveData<List<Character>>()
    val characterList: LiveData<List<Character>> = _characterList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _episodeTitle = MutableLiveData<String>()
    val episodeTitle: LiveData<String> = _episodeTitle

    fun fetchCharacters() {
        apiService.getCharacters().enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    response.body()?.characters?.let { characters ->
                        _characterList.postValue(characters)
                    } ?: _errorMessage.postValue("No data available")
                } else {
                    _errorMessage.postValue("Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                _errorMessage.postValue(t.localizedMessage ?: "Unexpected error")
            }
        })
    }

    fun fetchEpisodeTitle(url: String, callback: (String) -> Unit) {
        apiService.getEpisodeName(url).enqueue(object : Callback<Episode> {
            override fun onResponse(call: Call<Episode>, response: Response<Episode>) {
                callback(response.body()?.name ?: "Unknown")
            }

            override fun onFailure(call: Call<Episode>, t: Throwable) {
                callback("Unknown")
            }
        })
    }

    fun saveCharacterToHistory(character: Character) {
        character.episode?.firstOrNull()?.let { episodeUrl ->
            fetchEpisodeTitle(episodeUrl) { episodeName ->
                viewModelScope.launch(Dispatchers.IO) {
                    val entity = CharacterEntity(
                        id = character.id,
                        name = character.name,
                        status = character.status ?: "Undefined",
                        species = character.species ?: "Undefined",
                        gender = character.gender ?: "Undefined",
                        location = character.location?.name ?: "Unknown",
                        firstEpisodeName = episodeName,
                        origin = character.origin?.name ?: "Unknown",
                        imageBase64 = character.image?.let { ImageUtils.urlToBase64(it) }
                    )
                    database.insertCharacter(entity)
                }
            }
        }
    }

    fun retrieveViewedCharacters(): LiveData<List<CharacterEntity>> {
        return database.getAllViewedCharacters()
    }
}