package com.example.roomconrelaciones.ui.addJugador

import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador

sealed class AddJugadorEvent {
    class Addjugador(val jugador: Jugador, val equipo: Equipo?) : AddJugadorEvent()
    class GetEquipo(val equipoName: String) : AddJugadorEvent()
}