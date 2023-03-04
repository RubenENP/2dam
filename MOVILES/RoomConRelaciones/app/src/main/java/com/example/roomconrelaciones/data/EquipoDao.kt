package com.example.roomconrelaciones.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomconrelaciones.data.modelo.EquipoEntity
import com.example.roomconrelaciones.data.modelo.JugadorEntity

@Dao
interface EquipoDao {
    @Query(Constantes.SELECT_EQUIPOS)
    suspend fun getEquipos(): List<EquipoEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(equipoEntity: EquipoEntity)

    @Query(Constantes.DELETE_EQUIPOS)
    suspend fun delete(id: Int)

    @Query(Constantes.SELECT_JUGADORES)
    suspend fun getJugadores(id: Int): List<JugadorEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJugador(jugadorEntity: JugadorEntity)

    @Query(Constantes.DELETE_JUGADORES_DE_UN_EQUIPO)
    suspend fun deleteJugadores(idEquipo: Int)

    @Query(Constantes.DELETE_UN_JUGADOR)
    suspend fun deleteJugador(nombre: String)

    @Query(Constantes.UPDATE_EQUIPO)
    suspend fun updateEquipo(nombre: String, estadio: String, id: Int)
}