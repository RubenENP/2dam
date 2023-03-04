package com.example.flowsrubenhita.network.services

import com.example.flowsrubenhita.data.modelo.MovieDesc
import com.example.flowsrubenhita.data.modelo.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieDesc>

    @GET("/3/list/{list_id}")
    suspend fun getMovieList(@Path("list_id") id: Int) : Response<Movies>
}