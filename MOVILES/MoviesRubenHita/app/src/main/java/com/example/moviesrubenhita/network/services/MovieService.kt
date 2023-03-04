package com.example.moviesrubenhita.network.services

import com.example.moviesrubenhita.data.modelo.genre.Genres
import com.example.moviesrubenhita.data.modelo.movie.MovieDesc
import com.example.moviesrubenhita.data.modelo.movie.MovieQueryDesc
import com.example.moviesrubenhita.data.modelo.movie.Movies
import com.example.moviesrubenhita.data.modelo.movie.MoviesQuery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieDesc>

    @GET("/3/list/{list_id}")
    suspend fun getMovieList(@Path("list_id") id: Int) : Response<Movies>

    @GET("/3/genre/movie/list")
    suspend fun getGenresList(): Response<Genres>

    @GET("/3/search/movie")
    suspend fun getMovieListByName(@Query("query") name: String) :Response<MoviesQuery>
}