package com.example.moviescomposerubenhita.data.remote

import com.example.moviescomposerubenhita.data.NetworkResult
import com.example.moviescomposerubenhita.data.modelo.actors.Actors
import com.example.moviescomposerubenhita.data.modelo.movies.Movies
import com.example.moviescomposerubenhita.data.modelo.movies.MoviesQuery
import com.example.moviescomposerubenhita.data.modelo.series.Series
import com.example.moviescomposerubenhita.data.network.services.MovieService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService) :
    BaseApiResponse() {
    suspend fun fetchMovieList(id: Int): NetworkResult<Movies> {
        return safeApiCall(apiCall = { movieService.getTrendingMovieList(id) })
    }

    suspend fun fetchSeriesList(page: Int): NetworkResult<Series> {
        return safeApiCall(apiCall = { movieService.getTrendingSerieList(page) })
    }

    suspend fun fetchMovieListByName(name: String): NetworkResult<MoviesQuery> {
        return safeApiCall(apiCall = { movieService.getMovieListByName(name) })
    }

    suspend fun fetchActorsList(): NetworkResult<Actors> {
        return safeApiCall ( apiCall = {movieService.getActorsList()} )
    }
}