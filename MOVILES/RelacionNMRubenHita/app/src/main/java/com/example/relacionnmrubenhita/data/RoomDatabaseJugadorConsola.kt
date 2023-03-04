package com.example.relacionnmrubenhita.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.relacionnmrubenhita.data.modelo.ConsolaEntity
import com.example.relacionnmrubenhita.data.modelo.JugadorConsolaCrossRef
import com.example.relacionnmrubenhita.data.modelo.JugadorEntity

@Database(entities = [ConsolaEntity::class, JugadorEntity::class, JugadorConsolaCrossRef::class], version = 2, exportSchema = false)
abstract class RoomDatabaseJugadorConsola : RoomDatabase(){
    abstract val jugadorConsolaDao: JugadorConsolaDao
}