package com.example.navigationrubenhita.domain.modelo

data class Partido(
    val idPartido: Int,
    val equipoA: Equipo,
    val equipoB: Equipo,
    val ganador: Equipo?
)