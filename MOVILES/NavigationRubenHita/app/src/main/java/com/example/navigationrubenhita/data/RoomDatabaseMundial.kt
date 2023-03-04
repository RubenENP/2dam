package com.example.navigationrubenhita.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.navigationrubenhita.data.modelo.EquipoEntity
import com.example.navigationrubenhita.data.modelo.GrupoEntity

@Database(entities = [EquipoEntity::class, GrupoEntity::class], version = 2, exportSchema = false)
abstract class RoomDatabaseMundial : RoomDatabase(){
    abstract val mundialDao: MundialDao
}