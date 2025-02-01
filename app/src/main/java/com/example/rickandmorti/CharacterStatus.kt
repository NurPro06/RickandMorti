package com.example.rickandmorti

enum class CharacterStatus(val color: Int) {
    ALIVE(android.R.color.holo_green_light),
    DECEASED(android.R.color.holo_red_light),
    UNDEFINED(android.R.color.darker_gray)
}