package com.example.relacionnmrubenhita.data

import com.example.relacionnmrubenhita.data.modelo.*
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import javax.inject.Inject

class Repository @Inject constructor(private  val jugadorConsolaDao: JugadorConsolaDao){
    suspend fun insertConsola(consola: Consola) = jugadorConsolaDao.insert(consola.toEntity())

    suspend fun getConsolas() = jugadorConsolaDao.getConsolas().map { it.toConsola() }

    suspend fun deleteConsola(consola: Consola) = jugadorConsolaDao.deleteConsola(consola.toEntity())

    suspend fun getJugadores() = jugadorConsolaDao.getJugadores().map { it.toJugador() }

    suspend fun deleteJugador(jugador: Jugador) = jugadorConsolaDao.deleteJugador(jugador.toEntity())

    suspend fun insertJugador(jugador: Jugador) = jugadorConsolaDao.insertJugador(jugador.toEntity())

    suspend fun getUnaConsola(id: Int) = jugadorConsolaDao.getUnaConsola(id).toConsola()

    suspend fun addCrossRef(jugadorId: Int, consolaId: Int) = jugadorConsolaDao.insertCrossRef(JugadorConsolaCrossRef(jugadorId, consolaId))

    suspend fun getUnJugador(id: Int) = jugadorConsolaDao.getUnJugador(id).toJugador()
}