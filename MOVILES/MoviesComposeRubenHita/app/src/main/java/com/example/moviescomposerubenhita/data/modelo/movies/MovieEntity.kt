package com.example.moviescomposerubenhita.data.modelo.movies

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val image: String?,
    val favourite: Int,
    val seeLater: Int,
)