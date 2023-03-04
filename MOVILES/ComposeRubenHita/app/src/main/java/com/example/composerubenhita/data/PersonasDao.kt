package com.example.composerubenhita.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.composerubenhita.data.model.PersonaEntity

@Dao
interface PersonasDao {
    @Query("SELECT * FROM personas")
    suspend fun getAll(): List<PersonaEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(personaEntity: PersonaEntity)
}