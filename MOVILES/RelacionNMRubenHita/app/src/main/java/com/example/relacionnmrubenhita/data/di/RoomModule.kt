package com.example.relacionnmrubenhita.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.relacionnmrubenhita.data.Constantes
import com.example.relacionnmrubenhita.data.RoomDatabaseJugadorConsola
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
    @Named(Constantes.ASSETDB)
    fun getAssetDB() = Constantes.DB_RUTA


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named(Constantes.ASSETDB) ruta: String
    )  =
        Room.databaseBuilder(context, RoomDatabaseJugadorConsola::class.java, Constantes.ITEMS)
            .fallbackToDestructiveMigration()
            .createFromAsset(ruta)
            .build()

    @Provides
    fun providesPersonaDao(articlesDatabase: RoomDatabaseJugadorConsola) =
        articlesDatabase.jugadorConsolaDao
}