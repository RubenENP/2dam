package com.example.mundialrubenhita.domain.modelo

data class PartidoEliminatoria (
    val tipo: PartidoEliminatoriaTipo,
    val equipo1: Equipo,
    val equipo2: Equipo,
    val ganador: Equipo,
)