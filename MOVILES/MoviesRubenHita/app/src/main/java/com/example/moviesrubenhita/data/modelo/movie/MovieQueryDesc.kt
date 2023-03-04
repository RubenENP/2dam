package com.example.moviesrubenhita.data.modelo.movie

data class MovieQueryDesc (
    val id: Int,
    val original_title: String,
    val release_date: String,
    val vote_average: Double,
    val backdrop_path: String?,
)