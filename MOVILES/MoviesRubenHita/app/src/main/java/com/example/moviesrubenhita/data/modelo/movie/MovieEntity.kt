package com.example.moviesrubenhita.data.modelo.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val imgPath: String,
)
