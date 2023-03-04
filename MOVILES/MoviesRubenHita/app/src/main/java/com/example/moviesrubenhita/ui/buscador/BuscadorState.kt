package com.example.moviesrubenhita.ui.buscador

import com.example.moviesrubenhita.domain.modelo.Genre
import com.example.moviesrubenhita.domain.modelo.Movie

data class BuscadorState(
    val isLoading : Boolean = false,
    val error: String? = null,
    val moviesList: List<Movie>?=null,
    val index: Int = 1
)