package com.example.moviesrubenhita.domain.modelo

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val voteAverage: Double,
    val imgPath: String,
    val genreIds: List<Int>,
)