package com.example.moviescomposerubenhita.data.local

import androidx.room.*
import com.example.moviescomposerubenhita.data.modelo.movies.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getOneMovie(id: Int): MovieEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity)

    @Insert
    suspend fun insertAll(movieList: List<MovieEntity>)

    @Delete
    suspend fun deleteMovieList(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE favourite = 1 or seeLater = 1")
    suspend fun getAllFavoriteOrSeeLater(): List<MovieEntity>
}