package com.example.roomconrelaciones.ui.addEquipo

import com.example.roomconrelaciones.domain.modelo.Equipo

data class AddEquipoState (
    val equipo: Equipo= Equipo(0,"", "", mutableListOf()),
    val error: String? = "null",
)