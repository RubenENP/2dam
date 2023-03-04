package com.example.moviescomposerubenhita.ui.searchedMovies

import com.example.moviescomposerubenhita.domain.model.Movie

data class SearchedMovieListState(
    val listaPeliculas: List<Movie>? = null,
    val loading: Boolean = false,
    val error: String? = null
)

sealed class SearchEvent(){
    class LimpiarError : SearchEvent()
    class SearchMovie(val name: String): SearchEvent()
    class InsertFavorite(val movie: Movie): SearchEvent()
    class InsertSeeLater(val movie: Movie): SearchEvent()
}
