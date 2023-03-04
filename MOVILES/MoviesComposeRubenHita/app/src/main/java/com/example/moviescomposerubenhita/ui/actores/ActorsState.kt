package com.example.moviescomposerubenhita.ui.actores

import com.example.moviescomposerubenhita.domain.model.Actor

data class ActorsState(
    val listActors: List<Actor>?=null,
    val loading: Boolean = false,
    val error: String? = null
)

sealed class ActorsEvent() {
    class GetAll() : ActorsEvent()
}
