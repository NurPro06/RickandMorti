package com.example.rickandmorti.data

import com.example.rickandmorti.data.models.BaseResponse
import retrofit2.Call
import retrofit2.http.GET


interface CartoonApiService {

    @GET("character")
    fun getCharacters(): Call<BaseResponse>

}