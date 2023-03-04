package com.example.moviesrubenhita.data

import android.util.Log
import com.example.moviesrubenhita.data.local.MovieDao
import com.example.moviesrubenhita.data.modelo.MovieGenreCrossRef
import com.example.moviesrubenhita.data.modelo.genre.GenreDesc
import com.example.moviesrubenhita.data.modelo.genre.Genres
import com.example.moviesrubenhita.data.modelo.movie.MovieDesc
import com.example.moviesrubenhita.data.remote.MovieRemoteDataSource
import com.example.moviesrubenhita.domain.modelo.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GenresRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val dao: MovieDao,
) {
    fun fetchAllGenres(): Flow<NetworkResult<Genres>>{
        return flow {
            emit(NetworkResult.Loading())
            val movies = movieRemoteDataSource.fetchMovieList(3)
            if (movies is NetworkResult.Success) {
                dao.deleteAllMovies()
                movies.data?.items?.map{ it.toMovieEntity() }?.let { dao.insertAll(it) }
            }

            val result = movieRemoteDataSource.fetchGenders()
            emit(result)

            if (result is NetworkResult.Success){
                result.data?.let { lista ->
                    dao.deleteAllGenres(lista.genres.map { it.toGenreEntity() })
                    dao.insertList(lista.genres.map { it.toGenreEntity() })
                }
            }

            movies.data?.items?.let { insertCrossRef(it) }
        }
    }

    private suspend fun insertCrossRef(movies: List<MovieDesc>) {
        for (m in movies){
            for (ids in m.genre_ids){
                dao.insertCrossRef(MovieGenreCrossRef(m.id, ids))
                Log.e("TAG:::", MovieGenreCrossRef(m.id, ids).toString())
            }
        }

    }

    suspend fun getGenresFromCache(): List<Genre> {
        return dao.getAllGenres().map { it.toGenre() }
    }
}