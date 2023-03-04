package com.example.examen1evrubenhita.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.examen1evrubenhita.data.modelo.ComponenteEntity
import com.example.examen1evrubenhita.data.modelo.EquipoEntity

@Database(entities = [EquipoEntity::class, ComponenteEntity::class], version = 2, exportSchema = false)
abstract class RoomDBEquipos : RoomDatabase(){
    abstract val equipoDao: EquipoDao
}