package com.example.moviesrubenhita.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesrubenhita.data.modelo.MovieGenreCrossRef
import com.example.moviesrubenhita.data.modelo.genre.GenreEntity
import com.example.moviesrubenhita.data.modelo.movie.MovieEntity

@Database(entities = [MovieEntity::class, GenreEntity::class, MovieGenreCrossRef::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}