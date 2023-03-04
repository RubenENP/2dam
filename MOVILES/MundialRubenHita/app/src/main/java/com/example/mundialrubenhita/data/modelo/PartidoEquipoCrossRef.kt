package com.example.mundialrubenhita.data.modelo

import androidx.room.Entity

@Entity(primaryKeys = ["idPartido", "idEquipo"])
data class PartidoEquipoCrossRef(
    val idPartido: Int,
    val idEquipo: Int,
)