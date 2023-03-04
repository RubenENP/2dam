package com.example.mundialrubenhita.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mundialrubenhita.data.modelo.EquipoEntity
import com.example.mundialrubenhita.data.modelo.PartidoEntity
import com.example.mundialrubenhita.data.modelo.PartidoEquipoCrossRef
import com.example.mundialrubenhita.data.modelo.RegistroEntity

@Database(entities = [EquipoEntity::class, PartidoEntity::class, PartidoEquipoCrossRef::class, RegistroEntity::class],
    version = 2, exportSchema = false)
abstract class RoomDatabaseMundial : RoomDatabase(){
    abstract val mundialDao: MundialDao
}