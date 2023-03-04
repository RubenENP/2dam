package com.example.moviescomposerubenhita.ui.main

import com.example.moviescomposerubenhita.domain.model.Movie
import com.example.moviescomposerubenhita.domain.model.Serie

data class MainState (val movieList: List<Movie>?=null, val seriesList: List<Serie>?=null,val loading: Boolean=false, val error: String?=null)

sealed class MainEvent {
    class GetMovieList : MainEvent()
    class LimpiarError : MainEvent()
    class GetSerieList : MainEvent()
}