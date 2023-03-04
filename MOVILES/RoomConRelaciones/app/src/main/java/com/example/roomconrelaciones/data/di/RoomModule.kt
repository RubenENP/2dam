package com.example.roomconrelaciones.data.di

import android.content.Context
import androidx.room.Room
import com.example.roomconrelaciones.data.Constantes
import com.example.roomconrelaciones.data.EquiposRoomDatabase
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
    fun getAssetDB() = Constantes.RUTA_DB


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named(Constantes.ASSETDB) ruta: String
    )  =
        Room.databaseBuilder(context, EquiposRoomDatabase::class.java, Constantes.ITEMS)
            .fallbackToDestructiveMigration()
            .createFromAsset(ruta)
            .build()

    @Provides
    fun providesPersonaDao(articlesDatabase: EquiposRoomDatabase) =
        articlesDatabase.equipoDao()
}