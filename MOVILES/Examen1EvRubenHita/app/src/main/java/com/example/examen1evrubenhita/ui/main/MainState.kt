package com.example.examen1evrubenhita.ui.main

import com.example.examen1evrubenhita.domain.modelo.Equipo

data class MainState(
    val equiposList: List<Equipo>? = null,
    val error: String? = null
)