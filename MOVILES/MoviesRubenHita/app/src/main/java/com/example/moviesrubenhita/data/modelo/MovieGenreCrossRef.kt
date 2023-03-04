package com.example.moviesrubenhita.data.modelo

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenreCrossRef(
    val movieId: Int,
    val genreId: Int,
)
