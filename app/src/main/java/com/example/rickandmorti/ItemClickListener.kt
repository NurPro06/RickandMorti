package com.example.rickandmorti

interface ItemClickListener {
    fun onCharacterSelected(character: Character) {
        println("Character selected: ${character.name}")
    }

    fun onEntitySelected(entity: CharacterEntity) {
        println("Entity selected: ${entity.name}")
    }
}