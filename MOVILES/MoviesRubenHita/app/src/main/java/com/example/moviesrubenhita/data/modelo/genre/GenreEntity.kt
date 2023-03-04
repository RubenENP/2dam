package com.example.moviesrubenhita.data.modelo.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val genreId: Int,
    val name: String
)
