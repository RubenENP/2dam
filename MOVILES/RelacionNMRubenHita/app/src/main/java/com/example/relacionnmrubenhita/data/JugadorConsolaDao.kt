package com.example.relacionnmrubenhita.data

import androidx.room.*
import com.example.relacionnmrubenhita.data.modelo.*
import com.example.relacionnmrubenhita.domain.modelo.Consola

@Dao
interface JugadorConsolaDao {
    @Delete
    suspend fun deleteConsola(consola: ConsolaEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(consola: ConsolaEntity)

    @Transaction
    @Query(Constantes.SELECT_CONSOLAS)
    suspend fun getConsolas(): List<ConsolaWithJugadores>

    @Transaction
    @Query(Constantes.SELECT_JUGADORES)
    suspend fun getJugadores(): List<JugadorWithConsolas>

    @Delete
    suspend fun deleteJugador(jugador: JugadorEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJugador(jugador: JugadorEntity)

    @Transaction
    @Query("SELECT * FROM consolas where consolaId=:id")
    suspend fun getUnaConsola(id: Int): ConsolaWithJugadores

    @Insert
    suspend fun insertCrossRef(jugadorConsolaCrossRef: JugadorConsolaCrossRef)

    @Transaction
    @Query("SELECT * FROM jugadores where jugadorId=:id")
    suspend fun getUnJugador(id: Int): JugadorWithConsolas
}