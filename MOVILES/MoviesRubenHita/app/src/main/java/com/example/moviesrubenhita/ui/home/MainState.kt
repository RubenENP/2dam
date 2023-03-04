package com.example.moviesrubenhita.ui.home

import com.example.moviesrubenhita.domain.modelo.Movie

data class MainState (
    val movies: List<Movie>? = null,
    val isLoading : Boolean = false,
    val pageIndex: Int=1,
    val error: String? = null,
)