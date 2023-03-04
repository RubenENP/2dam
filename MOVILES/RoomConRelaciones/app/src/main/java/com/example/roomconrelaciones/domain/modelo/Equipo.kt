package com.example.roomconrelaciones.domain.modelo

data class Equipo(
    val id: Int,
    val nombre: String,
    val estadio: String,
    val jugadores: MutableList<Jugador>,
)
