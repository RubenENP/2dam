package com.example.mundialrubenhita.domain.modelo

data class Registro(
    val id: Int,
    val balance: Double,
    val dinero: Double,
    val idPartido: Int,
    var partido: Partido?
)