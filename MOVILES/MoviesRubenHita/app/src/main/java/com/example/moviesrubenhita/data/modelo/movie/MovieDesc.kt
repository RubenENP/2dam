package com.example.moviesrubenhita.data.modelo.movie

data class MovieDesc(
    val id: Int,
    val title: String,
    val release_date: String,
    val vote_average: Double,
    val backdrop_path: String,
    val genre_ids: List<Int>,
)