package com.example.moviescomposerubenhita.data.local

import androidx.room.*
import com.example.moviescomposerubenhita.data.modelo.actors.ActorEntity

@Dao
interface ActorsDao {
    @Query("SELECT * FROM actors")
    suspend fun getAll(): List<ActorEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(a: List<ActorEntity>)

    @Delete
    suspend fun deleteAll(a: List<ActorEntity>)
}