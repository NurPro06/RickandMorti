package com.example.rickandmorti.data

import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.episodes.Episode
import com.example.rickandmorti.data.models.BaseResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface CartoonApiService {

    @GET("character")
    fun getCharacters(): Call<BaseResponse>

    @GET("character")
    suspend fun getCharactersPaging(
        @Query("page") page: Int
    ): Response<BaseResponse>

    @GET
    fun getEpisodeName(@Url url: String): Call<Episode>
}