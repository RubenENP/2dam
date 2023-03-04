package com.example.navigationrubenhita.data.di

import android.content.Context
import androidx.room.Room
import com.example.navigationrubenhita.data.Constantes
import com.example.navigationrubenhita.data.RoomDatabaseMundial
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
        Room.databaseBuilder(context, RoomDatabaseMundial::class.java, Constantes.ITEMS)
            .fallbackToDestructiveMigration()
            .createFromAsset(ruta)
            .build()

    @Provides
    fun providesPersonaDao(articlesDatabase: RoomDatabaseMundial) =
        articlesDatabase.mundialDao
}