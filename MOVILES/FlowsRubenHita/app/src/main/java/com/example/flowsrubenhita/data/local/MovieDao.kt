package com.example.flowsrubenhita.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.flowsrubenhita.data.modelo.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie WHERE id = :id")
    fun getOneMovie(id: Int): MovieEntity

    @Insert
    fun insert(movieEntity: MovieEntity)

    @Insert
    fun insertAll(movieList: List<MovieEntity>)

    @Delete
    fun deleteMovieList(movieList: List<MovieEntity>)
}