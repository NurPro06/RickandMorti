package com.example.rickandmorti.data

import com.example.rickandmorti.paging.CharacterPagingSource
import jakarta.inject.Inject

class CharacterRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getPagingSource(): CharacterPagingSource {
        return CharacterPagingSource(apiService)
    }
    suspend fun getCharacterById(id: Int): Character {
        return apiService.getCharacterById(id)
    }
}
