package com.example.examen1evrubenhita.data.di

import android.content.Context
import androidx.room.Room
import com.example.examen1evrubenhita.data.RoomDBEquipos
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
    @Named("assetDB")
    fun getAssetDB() = "database/equipos.db"


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named("assetDB") ruta: String
    )  =
        Room.databaseBuilder(context, RoomDBEquipos::class.java, "items")
            .fallbackToDestructiveMigration()
            .createFromAsset(ruta)
            .build()

    @Provides
    fun providesPersonaDao(articlesDatabase: RoomDBEquipos) =
        articlesDatabase.equipoDao
}