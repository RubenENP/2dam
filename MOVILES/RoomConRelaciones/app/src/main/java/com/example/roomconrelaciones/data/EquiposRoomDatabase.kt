package com.example.roomconrelaciones.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomconrelaciones.data.modelo.EquipoEntity
import com.example.roomconrelaciones.data.modelo.JugadorEntity

@Database(entities = [EquipoEntity::class, JugadorEntity::class], version = 12, exportSchema = true)
abstract class EquiposRoomDatabase : RoomDatabase(){
    abstract fun equipoDao():EquipoDao
}