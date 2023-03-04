package com.example.moviesrubenhita.ui.genre

sealed class GenerosEvent {
    class Filter(val genre: String) : GenerosEvent()
    object GetGenres : GenerosEvent()
    object GetMovies : GenerosEvent()
}