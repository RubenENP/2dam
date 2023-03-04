package com.example.moviesrubenhita.ui.buscador

sealed class BuscadorEvent {
    class GetMoviesByName(val name: String) : BuscadorEvent()
}