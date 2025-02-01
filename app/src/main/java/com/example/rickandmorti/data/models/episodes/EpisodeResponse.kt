package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.models.episodes


import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("info")
    val info: Info? = null,
    @SerializedName("results")
    val episodes: List<Episode>? = null
)