package com.example.roomconrelaciones.domain.usescases

import com.example.roomconrelaciones.data.Repository
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador
import javax.inject.Inject

class AddJugador @Inject constructor(val repository: Repository) {
    suspend fun invoke(jugador: Jugador, equipo: Equipo) = repository.insertJugador(jugador, equipo)
}