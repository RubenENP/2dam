package com.example.relacionnmrubenhita.data.modelo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class JugadorWithConsolas (
    @Embedded val jugador: JugadorEntity,
    @Relation(
        parentColumn = "jugadorId",
        entityColumn = "consolaId",
        associateBy = Junction(JugadorConsolaCrossRef::class)
    )
    val consolas: List<ConsolaEntity>
)