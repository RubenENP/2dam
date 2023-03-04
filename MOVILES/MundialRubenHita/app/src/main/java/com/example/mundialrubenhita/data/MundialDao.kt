package com.example.mundialrubenhita.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.mundialrubenhita.data.modelo.*
import com.example.mundialrubenhita.domain.modelo.Registro

@Dao
interface MundialDao {
    @Query("SELECT * FROM equipos")
    suspend fun getAllEquipos(): List<EquipoEntity>

    @Insert
    suspend fun insertListPartidos(partidos: List<PartidoEntity>)

    @Transaction
    @Query("SELECT * FROM partidos")
    suspend fun getAllPartidos(): List<PartidoWithEquipos>

    @Transaction
    @Query("SELECT * FROM partidos WHERE resultado IS NULL")
    suspend fun getPartidosSinJugar(): List<PartidoWithEquipos>

    @Insert
    suspend fun insertCrossRef(partidoEquipoCrossRef: PartidoEquipoCrossRef)

    @Query("SELECT idPartido FROM partidos")
    suspend fun getPartidosId(): List<Int>

    @Transaction
    @Query("SELECT * FROM partidos WHERE idPartido=:id")
    suspend fun getUnPartido(id: Int): PartidoWithEquipos

    @Transaction
    @Query("SELECT * FROM registros")
    suspend fun getAllRegistros(): List<RegistroWithPartido>

    @Insert
    suspend fun insertRegistro(registro: RegistroEntity)

    @Update
    suspend fun updatePartido(partido: PartidoEntity)
}