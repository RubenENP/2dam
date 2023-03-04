package com.example.moviesrubenhita.data.remote

import com.example.flowsrubenhita.data.remote.BaseApiResponse
import com.example.moviesrubenhita.data.NetworkResult
import com.example.moviesrubenhita.data.modelo.movie.MovieDesc
import com.example.moviesrubenhita.data.modelo.movie.Movies
import com.example.moviesrubenhita.network.services.MovieService
import com.example.moviesrubenhita.data.modelo.genre.Genres
import com.example.moviesrubenhita.data.modelo.movie.MoviesQuery
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) :
    BaseApiResponse() {

//    suspend fun fetchTrendingMovies(): NetworkResult<List<Movie>> {
//
//        return safeApiCall(apiCall = { movieService.getPopularMovies() },
//            transform = { trendingMovieResponse ->
//                trendingMovieResponse
//                    .results?.map { movieEntity -> movieEntity.toMovie() } ?: emptyList()
//            })
//
//    }

    suspend fun fetchMovie(id: Int): NetworkResult<MovieDesc> {
        return safeApiCall(apiCall = { movieService.getMovie(id) })
    }

    suspend fun fetchMovieList(id: Int): NetworkResult<Movies> {
        return safeApiCall(apiCall = { movieService.getMovieList(id) })
    }

    suspend fun fetchGenders(): NetworkResult<Genres>{
        return safeApiCall(apiCall = { movieService.getGenresList() })
    }

    suspend fun fetchMovieListByName(name: String): NetworkResult<MoviesQuery> {
        return safeApiCall(apiCall = { movieService.getMovieListByName(name) })
    }
}