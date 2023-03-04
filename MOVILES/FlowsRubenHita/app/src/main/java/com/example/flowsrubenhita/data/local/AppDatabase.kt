package com.example.flowsrubenhita.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flowsrubenhita.data.modelo.MovieEntity

@Database(entities = [MovieEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}