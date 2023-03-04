package com.example.relacionnmrubenhita.data.modelo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ConsolaWithJugadores (
    @Embedded val consola: ConsolaEntity,
    @Relation(
        parentColumn = "consolaId",
        entityColumn = "jugadorId",
        associateBy = Junction(JugadorConsolaCrossRef::class)
    )
    val jugadores: List<JugadorEntity>
)