package com.example.roomrubenhita.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomrubenhita.data.modelo.FutbolistaEntity

@Database(entities = [FutbolistaEntity::class], version = 10, exportSchema = true)
abstract class FutbolistaRoomDatabase : RoomDatabase() {
    abstract fun futbolistaDao(): FutbolistaDao

    companion object {
        @Volatile
        private var INSTANCE: FutbolistaRoomDatabase? = null

        fun getDatabase(context: Context): FutbolistaRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FutbolistaRoomDatabase::class.java,
                    "items"
                )
                    .createFromAsset("database/futbolistas.db")
                    .fallbackToDestructiveMigrationFrom(1)
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}