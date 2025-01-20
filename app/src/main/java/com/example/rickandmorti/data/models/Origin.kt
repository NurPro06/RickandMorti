package com.example.rickandmorti.data.models


import com.google.gson.annotations.SerializedName

data class Origin(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)