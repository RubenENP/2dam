package com.example.examen1evrubenhita.domain.modelo

data class Equipo(
    val id: Int,
    val nombre: String,
    val nacionalidad: String,
    val puesto: Int,
    val componentesList: List<Componente>?
)