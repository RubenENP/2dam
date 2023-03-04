package com.example.flowsrubenhita.data

import com.example.flowsrubenhita.data.local.MovieDao
import com.example.flowsrubenhita.data.modelo.MovieDesc
import com.example.flowsrubenhita.data.modelo.Movies
import com.example.flowsrubenhita.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

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
                    movieDao.deleteMovieList(lista.items.map { it.toMovieEntity() })
                    movieDao.insertAll(lista.items.map { it.toMovieEntity() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}