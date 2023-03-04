package com.example.flowsrubenhita.data

import com.example.flowsrubenhita.data.modelo.MovieDesc
import com.example.flowsrubenhita.data.modelo.MovieEntity
import com.example.flowsrubenhita.domain.modelo.Movie

fun MovieDesc.toMovie() : Movie = Movie(this.id, this.title)

fun MovieDesc.toMovieEntity() : MovieEntity = MovieEntity(this.id, this.title)
