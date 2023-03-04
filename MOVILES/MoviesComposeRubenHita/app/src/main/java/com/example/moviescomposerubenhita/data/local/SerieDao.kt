package com.example.moviescomposerubenhita.data.local

import androidx.compose.ui.text.style.LineBreak
import androidx.room.*
import com.example.moviescomposerubenhita.data.modelo.series.SerieEntity

@Dao
interface SerieDao {
    @Delete
    suspend fun deleteList(series: List<SerieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(series: List<SerieEntity>)

    @Query("SELECT * FROM series")
    suspend fun getAll() : List<SerieEntity>
}