package com.example.moviescomposerubenhita.data

import com.example.moviescomposerubenhita.data.local.ActorsDao
import com.example.moviescomposerubenhita.data.local.MovieDao
import com.example.moviescomposerubenhita.data.local.SerieDao
import com.example.moviescomposerubenhita.data.modelo.actors.Actors
import com.example.moviescomposerubenhita.data.modelo.movies.MovieEntity
import com.example.moviescomposerubenhita.data.modelo.movies.MovieQuery
import com.example.moviescomposerubenhita.data.modelo.movies.Movies
import com.example.moviescomposerubenhita.data.modelo.movies.MoviesQuery
import com.example.moviescomposerubenhita.data.modelo.series.Series
import com.example.moviescomposerubenhita.data.remote.MovieRemoteDataSource
import com.example.moviescomposerubenhita.domain.model.Actor
import com.example.moviescomposerubenhita.domain.model.Movie
import com.example.moviescomposerubenhita.domain.model.Serie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao,
    private val serieDao: SerieDao,
    private val actorsDao: ActorsDao
) {

    fun fetchTrendingMovieList(id: Int): Flow<NetworkResult<Movies>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchMovieList(id)
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let { lista ->
                    movieDao.deleteMovieList(lista.results.map { it.toMovieEntity() })
                    movieDao.insertAll(lista.results.map { it.toMovieEntity() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviesFromCache(): List<Movie> = movieDao.getAll().map { it.toMovie() }

    fun fetchTrendingSerieList(page: Int): Flow<NetworkResult<Series>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchSeriesList(page)
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let { lista ->
                    serieDao.deleteList(lista.results.map { it.toSerieEntity() })
                    serieDao.insertList(lista.results.map { it.toSerieEntity() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSeriesFromCache(): List<Serie> {
        return serieDao.getAll().map { it.toSerie() }
    }

    fun fetchMovieListByName(name: String): Flow<NetworkResult<MoviesQuery>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchMovieListByName(name)
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun insertMovie(movie: Movie) = movieDao.insert(movie.toMovieEntity())

    suspend fun getFavouriteOrSeeLaterMovies() = movieDao.getAllFavoriteOrSeeLater().map { it.toMovie() }

    fun fetchActorsList(): Flow<NetworkResult<Actors>>{
        return flow {
            emit(NetworkResult.Loading())
            val result = movieRemoteDataSource.fetchActorsList()
            emit(result)
            if (result is NetworkResult.Success) {
                result.data?.let { lista ->
                    actorsDao.deleteAll(lista.results.map { it.toActorEntity() })
                    actorsDao.insertAll(lista.results.map { it.toActorEntity() })
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getActorsCache(): List<Actor> = actorsDao.getAll().map { it.toActor() }
}
