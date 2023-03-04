package com.example.moviescomposerubenhita.data.di

import android.content.Context
import androidx.room.Room
import com.example.moviescomposerubenhita.data.local.ActorsDao
import com.example.moviescomposerubenhita.data.local.AppDatabase
import com.example.moviescomposerubenhita.data.local.MovieDao
import com.example.moviescomposerubenhita.data.local.SerieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    fun provideSerieDao(appDatabase: AppDatabase): SerieDao{
        return appDatabase.serieDao()
    }

    @Provides
    fun provideActorsDao(appDatabase: AppDatabase): ActorsDao{
        return appDatabase.actorsDao()
    }
}