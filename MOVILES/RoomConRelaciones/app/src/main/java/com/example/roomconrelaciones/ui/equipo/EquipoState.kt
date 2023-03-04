package com.example.roomconrelaciones.ui.equipo

import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador

data class EquipoState (
    val equipo: Equipo? = null,
    val error: String? = null,
)