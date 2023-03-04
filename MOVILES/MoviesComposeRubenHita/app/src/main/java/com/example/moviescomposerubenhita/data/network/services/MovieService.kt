package com.example.moviescomposerubenhita.data.network.services

import com.example.moviescomposerubenhita.data.modelo.actors.Actors
import com.example.moviescomposerubenhita.data.modelo.movies.MovieDesc
import com.example.moviescomposerubenhita.data.modelo.movies.Movies
import com.example.moviescomposerubenhita.data.modelo.movies.MoviesQuery
import com.example.moviescomposerubenhita.data.modelo.series.Series
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieDesc>

    @GET("/3/trending/all/week")
    suspend fun getTrendingMovieList(@Query("page") page: Int) : Response<Movies>

    @GET("/3/tv/popular")
    suspend fun getTrendingSerieList(@Query("page") page: Int) : Response<Series>

    @GET("/3/search/movie")
    suspend fun getMovieListByName(@Query("query") name: String) :Response<MoviesQuery>

    @GET("/3/person/popular")
    suspend fun getActorsList(): Response<Actors>
}