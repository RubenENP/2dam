package com.example.examen1evrubenhita.ui.addEquipo

import com.example.examen1evrubenhita.domain.modelo.Equipo

data class AddEquipoState(
    val equipo: Equipo? = null,
    val error: String? = null,
)