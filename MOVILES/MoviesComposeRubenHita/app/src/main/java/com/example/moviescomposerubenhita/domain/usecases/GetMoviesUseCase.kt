package com.example.moviescomposerubenhita.domain.usecases

import com.example.moviescomposerubenhita.data.NetworkResult
import com.example.moviescomposerubenhita.data.Repository
import com.example.moviescomposerubenhita.data.modelo.movies.Movies
import com.example.moviescomposerubenhita.data.modelo.movies.MoviesQuery
import com.example.moviescomposerubenhita.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

class GetMoviesUseCase @Inject constructor(
    private val repository: Repository,
) {
    fun getTrendingMovieList(): Flow<NetworkResult<Movies>>{
        return repository.fetchTrendingMovieList(Random.nextInt(1,20))
    }

    suspend fun getMovieListFromCache(): List<Movie>{
        return repository.getMoviesFromCache()
    }

    fun searchMovieList(name: String): Flow<NetworkResult<MoviesQuery>> {
        return repository.fetchMovieListByName(name)
    }

    suspend fun getFavouriteOrSeeLaterMovies(): List<Movie>{
        return repository.getFavouriteOrSeeLaterMovies()
    }
}