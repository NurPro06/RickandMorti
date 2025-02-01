    package com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room

    import androidx.room.Database
    import androidx.room.RoomDatabase
    import com.example.rickandmorti.room.CharacterDao
    import com.example.rickandmorti.room.CharacterEntity


    @Database(entities = [CharacterEntity::class], version = 4)
    abstract class CharacterDatabase : RoomDatabase() {

        abstract fun charactersDao(): CharacterDao
    }