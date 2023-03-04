package com.example.roomconrelaciones.data

import com.example.roomconrelaciones.data.modelo.toEntity
import com.example.roomconrelaciones.data.modelo.toEquipo
import com.example.roomconrelaciones.data.modelo.toJugador
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador
import javax.inject.Inject

class Repository @Inject constructor(private val equipoDao: EquipoDao) {
    suspend fun getAllEquipos() = equipoDao.getEquipos().map { it.toEquipo() }

    suspend fun insertEquipo(equipo: Equipo) = equipoDao.insert(equipo.toEntity())

    suspend fun deleteEquipo(equipo: Equipo) = equipoDao.delete(equipo.toEntity().id)

    suspend fun getJugadores(equipo: Equipo) = equipoDao.getJugadores(equipo.toEntity().id).map { it.toJugador() }

    suspend fun insertJugador(jugador: Jugador, equipo: Equipo) = equipoDao.insertJugador(jugador.toEntity(equipo.id))

    suspend fun deleteJugadoresDeUnEquipo(idEquipo: Int) = equipoDao.deleteJugadores(idEquipo)

    suspend fun deleteJugador(jugador: Jugador, equipo: Equipo) = equipoDao.deleteJugador(jugador.toEntity(equipo.id).nombre)

    suspend fun updateEquipo(nombre: String, estadio: String, id: Int) = equipoDao.updateEquipo(nombre, estadio, id)
}