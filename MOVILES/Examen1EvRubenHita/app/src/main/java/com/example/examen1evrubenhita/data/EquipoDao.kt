package com.example.examen1evrubenhita.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.examen1evrubenhita.data.modelo.EquipoEntity
import com.example.examen1evrubenhita.data.modelo.EquipoWithComponentes

@Dao
interface EquipoDao {
    @Transaction
    @Query("SELECT * FROM equipos order by puesto")
    suspend fun getEquipos(): List<EquipoWithComponentes>

    @Delete
    suspend fun deleteEquipo(toEntity: EquipoEntity)

    @Insert
    suspend fun insertEquipo(equipoEntity: EquipoEntity)

    @Transaction
    @Query("SELECT * FROM equipos where id=:idEquipo")
    suspend fun getUnEquipo(idEquipo: Int): EquipoWithComponentes
}