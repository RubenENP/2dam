package com.example.roomconrelaciones.ui.equipo

import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador

sealed class EquipoEvent {
    class GetEquipo(val nombre: String?): EquipoEvent()
    class BorrarJugador(val jugador: Jugador) : EquipoEvent()
    class AddJugador(val jugador: Jugador, val equipo: Equipo) : EquipoEvent()
    class UpdateEquipo(val nombre: String, val estadio: String, val id: Int?) :EquipoEvent()
}