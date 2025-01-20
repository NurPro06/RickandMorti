package com.example.rickandmorti.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorti.data.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {
    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character
    fun loadCharacter(id: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getCharacterById(id)
                _character.value = result
            } catch (e: Exception) {
                Log.e("oololo", "Error loading character", e)
            }
        }
    }
}