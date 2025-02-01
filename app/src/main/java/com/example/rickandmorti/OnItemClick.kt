package com.example.rickandmorti

import com.example.rickandmorti.room.CharacterEntity

interface OnItemClick {
    fun onItemClick(character: Character){
        println()
    }
    fun onItemClick(character: CharacterEntity){
        println()
    }
}