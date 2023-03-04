package com.example.flowsrubenhita.data.remote

import com.example.flowsrubenhita.data.NetworkResult
import com.example.flowsrubenhita.data.modelo.MovieDesc
import com.example.flowsrubenhita.data.modelo.Movies
import com.example.flowsrubenhita.domain.modelo.Movie
import com.example.flowsrubenhita.network.services.MovieService
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
}