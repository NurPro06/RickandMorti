package com.example.rickandmorti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorti.data.RetrofitInstance
import com.example.rickandmorti.data.models.BaseResponse
import com.example.rickandmorti.data.models.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val api = RetrofitInstance.api

    private val _charactersData = MutableLiveData<List<Character>>()
    val charactersData: LiveData<List<Character>> get() = _charactersData

    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String> get() = _errorData

    fun getCharacters() {
        api.getCharacters().enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    _charactersData.postValue(response.body()?.characters ?: emptyList())
                } else {
                    _errorData.postValue("Error: ${response.errorBody()?.string() ?: "Unknown error"}")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                _errorData.postValue(t.message ?: "Unknown error")
            }
        })
    }
}
