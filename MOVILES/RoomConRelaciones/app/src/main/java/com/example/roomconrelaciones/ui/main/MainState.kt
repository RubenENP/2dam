package com.example.roomconrelaciones.ui.main

import com.example.roomconrelaciones.domain.modelo.Equipo

data class MainState (
    var listEquipos:List<Equipo> = emptyList(),
    var error: String? = null,
)