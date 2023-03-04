package com.example.moviesrubenhita.data

import android.util.Log
import com.example.moviesrubenhita.data.local.MovieDao
import com.example.moviesrubenhita.data.modelo.movie.MovieDesc
import com.example.moviesrubenhita.data.modelo.movie.Movies
import com.example.moviesrubenhita.data.modelo.movie.MoviesQuery
import com.example.moviesrubenhita.data.remote.MovieRemoteDataSource
import com.example.moviesrubenhita.domain.modelo.Movie
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao,
) {
    fun fetchOneMovie(id: Int): Flow<NetworkResult<MovieDesc>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchMovie(id)
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let {
                    movieDao.insert(it.toMovieEntity())
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    fun fetchMovieList(id: Int): Flow<NetworkResult<Movies>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchMovieList(id)
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let { lista ->
                    movieDao.deleteAllMovies()
                    movieDao.insertAll(lista.items.map { it.toMovieEntity() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviesFromCache(): List<Movie>{
        return movieDao.getAll().map { it.toMovie() }
    }


    fun fetchMovieListByName(name: String): Flow<NetworkResult<MoviesQuery>>{
        return flow {
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchMovieListByName(name)
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let { lista ->
                    movieDao.deleteAllMovies()
                    movieDao.insertAll(lista.results.map { it.toMovieEntity() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}