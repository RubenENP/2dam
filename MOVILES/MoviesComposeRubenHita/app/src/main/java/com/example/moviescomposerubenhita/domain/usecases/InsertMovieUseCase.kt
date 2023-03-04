package com.example.moviescomposerubenhita.domain.usecases

import com.example.moviescomposerubenhita.data.Repository
import com.example.moviescomposerubenhita.data.modelo.movies.MovieEntity
import com.example.moviescomposerubenhita.domain.model.Movie
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun insertFavorite(movie: Movie) =
        repository.insertMovie(Movie(movie.nombre, movie.image, 1, 0))

    suspend fun insertSeeLater(movie: Movie) =
        repository.insertMovie(Movie(movie.nombre, movie.image, 0, 1))

}