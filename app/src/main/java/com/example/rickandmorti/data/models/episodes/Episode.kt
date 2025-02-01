package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.episodes


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Episode(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("episode")
    val episode: String? = null,
    @SerializedName("characters")
    val characters: List<String?>? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("created")
    val created: String? = null
)