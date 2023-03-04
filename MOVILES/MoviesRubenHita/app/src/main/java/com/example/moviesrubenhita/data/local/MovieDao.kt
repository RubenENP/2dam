package com.example.moviesrubenhita.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviesrubenhita.data.modelo.MovieGenreCrossRef
import com.example.moviesrubenhita.data.modelo.genre.GenreEntity
import com.example.moviesrubenhita.data.modelo.genre.GenreWithMovies
import com.example.moviesrubenhita.data.modelo.movie.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie WHERE movieId = :id")
    suspend fun getOneMovie(id: Int): MovieEntity

    @Insert
    suspend fun insert(movieEntity: MovieEntity)

    @Insert
    suspend fun insertAll(movieList: List<MovieEntity>)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<MovieEntity>

    //Genres
    @Query("SELECT * FROM genre")
    suspend fun getAllGenres(): List<GenreWithMovies>

    @Insert
    suspend fun insertList(genres: List<GenreEntity>)

    @Delete
    suspend fun deleteAllGenres(genres: List<GenreEntity>)

    @Insert
    suspend fun insertCrossRef(crossRef: MovieGenreCrossRef)
}