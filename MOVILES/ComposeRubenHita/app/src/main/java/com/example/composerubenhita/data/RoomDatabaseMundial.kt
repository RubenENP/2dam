package com.example.composerubenhita.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composerubenhita.data.model.PersonaEntity

@Database(entities = [PersonaEntity::class],
    version = 3, exportSchema = false)
abstract class RoomDatabaseMundial : RoomDatabase(){
    abstract val personasDao: PersonasDao
}