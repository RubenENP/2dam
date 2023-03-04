package com.example.relacionnmrubenhita.data.modelo

import androidx.room.Entity

@Entity(primaryKeys = ["jugadorId", "consolaId"])
data class JugadorConsolaCrossRef(
    val jugadorId: Int,
    val consolaId: Int,
)