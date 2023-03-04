package com.example.moviesrubenhita.domain.useCases

import android.util.Log
import com.example.moviesrubenhita.data.NetworkResult
import com.example.moviesrubenhita.data.Repository
import com.example.moviesrubenhita.data.modelo.movie.Movies
import com.example.moviesrubenhita.data.modelo.movie.MoviesQuery
import com.example.moviesrubenhita.domain.modelo.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: Repository) {
    fun fetchMovieList(id: Int): Flow<NetworkResult<Movies>> {
        return repository.fetchMovieList(id)
    }

    fun fetchMovieListByName(name: String) : Flow<NetworkResult<MoviesQuery>> {
        return repository.fetchMovieListByName(name)
    }

    suspend fun getMoviesFromCache(): List<Movie>{
        return repository.getMoviesFromCache()
    }
}