package com.example.mundialrubenhita.domain.modelo

data class Partido(
    val idPartido: Int,
    val equipoLocal: Equipo,
    val equipoVisitante: Equipo,
    var golesLocal: Int?,
    var golesVisitante: Int?,
    var resultado: Int?,
)