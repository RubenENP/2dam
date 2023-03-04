package com.example.moviescomposerubenhita.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviescomposerubenhita.data.modelo.actors.ActorEntity
import com.example.moviescomposerubenhita.data.modelo.movies.MovieEntity
import com.example.moviescomposerubenhita.data.modelo.series.SerieEntity

@Database(entities = [MovieEntity::class, SerieEntity::class, ActorEntity::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun serieDao(): SerieDao
    abstract fun actorsDao(): ActorsDao
}