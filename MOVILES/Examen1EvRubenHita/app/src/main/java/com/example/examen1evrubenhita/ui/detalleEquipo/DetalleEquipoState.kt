package com.example.examen1evrubenhita.ui.detalleEquipo

import com.example.examen1evrubenhita.domain.modelo.Equipo

data class DetalleEquipoState(
    val equipo: Equipo? = null,
    val error: String? = null,
)