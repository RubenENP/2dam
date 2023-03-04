package com.example.moviesrubenhita.data

import com.example.moviesrubenhita.data.modelo.genre.GenreDesc
import com.example.moviesrubenhita.data.modelo.genre.GenreEntity
import com.example.moviesrubenhita.data.modelo.genre.GenreWithMovies
import com.example.moviesrubenhita.data.modelo.movie.MovieDesc
import com.example.moviesrubenhita.data.modelo.movie.MovieEntity
import com.example.moviesrubenhita.data.modelo.movie.MovieQueryDesc
import com.example.moviesrubenhita.domain.modelo.Genre
import com.example.moviesrubenhita.domain.modelo.Movie

fun MovieDesc.toMovie(): Movie =
    Movie(this.id, this.title, this.release_date, this.vote_average, this.backdrop_path, this.genre_ids)

fun MovieDesc.toMovieEntity(): MovieEntity =
    MovieEntity(this.id, this.title, this.release_date, this.vote_average, this.backdrop_path)

fun MovieEntity.toMovie(): Movie =
    Movie(this.movieId, this.title, this.releaseDate, this.voteAverage, this.imgPath, emptyList())

fun GenreDesc.toGenre(): Genre = Genre(this.id, this.name, emptyList())

fun GenreDesc.toGenreEntity(): GenreEntity = GenreEntity(this.id, this.name)

fun GenreWithMovies.toGenre(): Genre = Genre(this.genre.genreId, this.genre.name, this.movies.map { it.toMovie() })

fun MovieQueryDesc.toMovieEntity(): MovieEntity = MovieEntity(
    this.id, this.original_title, this.release_date,
    this.vote_average, this.backdrop_path ?: ""
)

fun MovieQueryDesc.toMovie(): Movie = Movie(
    this.id, this.original_title, this.release_date,
    this.vote_average, this.backdrop_path ?: "", emptyList()
)
