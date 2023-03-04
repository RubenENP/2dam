package com.example.composerubenhita.data.di

import android.content.Context
import androidx.room.Room
import com.example.composerubenhita.data.RoomDatabaseMundial
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Named("assetsDB")
    fun getAssetDB() = "database/personasDB.db"


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("assetsDB") ruta: String
    )  =
        Room.databaseBuilder(context, RoomDatabaseMundial::class.java, "items")
            .fallbackToDestructiveMigration()
            .createFromAsset(ruta)
            .build()

    @Provides
    fun providesPersonaDao(articlesDatabase: RoomDatabaseMundial) =
        articlesDatabase.personasDao
}