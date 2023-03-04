package com.example.moviescomposerubenhita.ui.biblioteca

import com.example.moviescomposerubenhita.domain.model.Movie

data class BibliotecaState(val favoriteList: List<Movie> = emptyList(), val seeLaterList: List<Movie> = emptyList(), val error: String?=null)

sealed class BibliotecaEvent(){
    class GetAll(): BibliotecaEvent()
}
