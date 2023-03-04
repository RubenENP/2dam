package com.example.moviesrubenhita.ui.genre

import com.example.moviesrubenhita.domain.modelo.Genre
import com.example.moviesrubenhita.domain.modelo.Movie

data class GenerosState(val genreList: List<Genre>? = null, val error: String? = null, val isLoading: Boolean = false,
val movieList: List<Movie>?=null)