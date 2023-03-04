package com.example.relacionnmrubenhita.domain.modelo

data class Consola(
    val id: Int,
    val nombre: String,
    val marca: String,
    val precio: Int,
    val jugadoresList: List<Jugador>,
)