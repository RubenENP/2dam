package com.example.moviescomposerubenhita.data

import com.example.moviescomposerubenhita.data.modelo.actors.ActorDesc
import com.example.moviescomposerubenhita.data.modelo.actors.ActorEntity
import com.example.moviescomposerubenhita.data.modelo.movies.MovieDesc
import com.example.moviescomposerubenhita.data.modelo.movies.MovieEntity
import com.example.moviescomposerubenhita.data.modelo.movies.MovieQuery
import com.example.moviescomposerubenhita.data.modelo.series.SerieDesc
import com.example.moviescomposerubenhita.data.modelo.series.SerieEntity
import com.example.moviescomposerubenhita.domain.model.Actor
import com.example.moviescomposerubenhita.domain.model.Movie
import com.example.moviescomposerubenhita.domain.model.Serie

fun MovieDesc.toMovie(): Movie {
    return if (this.name != null) {
        Movie(this.name, this.backdrop_path,0,0)
    } else if (this.title != null) {
        Movie(this.title, this.backdrop_path,0,0)
    } else {
        Movie("", this.backdrop_path,0,0)
    }
}

fun MovieDesc.toMovieEntity(): MovieEntity {
    return if (this.name != null) {
        MovieEntity(0, this.name, this.backdrop_path, favourite = 0, seeLater = 0)
    } else if (this.title != null) {
        MovieEntity(0, this.title, this.backdrop_path, favourite = 0, seeLater = 0)
    } else {
        MovieEntity(0, "", this.backdrop_path, favourite = 0, seeLater = 0)
    }
}

fun MovieQuery.toMovie() = Movie(this.original_title, this.backdrop_path,0,0)

fun MovieEntity.toMovie(): Movie = Movie(this.title, this.image, this.favourite, this.seeLater)

fun SerieDesc.toSerieEntity(): SerieEntity = SerieEntity(this.id, this.name, this.backdrop_path)

fun SerieEntity.toSerie(): Serie = Serie(this.id, this.name, this.image)

fun SerieDesc.toSerie(): Serie = Serie(this.id, this.name, this.backdrop_path)

fun Movie.toMovieEntity() = MovieEntity(0,this.nombre, this.image, this.favourite, this.seeLater)

fun ActorDesc.toActorEntity() = ActorEntity(0, this.name, this.profile_path)

fun ActorDesc.toActor() = Actor(this.name, this.profile_path)

fun ActorEntity.toActor() = Actor(this.name, this.image)