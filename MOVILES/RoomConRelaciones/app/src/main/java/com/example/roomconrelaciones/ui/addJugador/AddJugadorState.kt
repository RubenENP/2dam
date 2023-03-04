package com.example.roomconrelaciones.ui.addJugador

import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador

data class AddJugadorState (
    val equipo: Equipo? = null,
    val jugador: Jugador? = null,
    val error: String? = null,
)