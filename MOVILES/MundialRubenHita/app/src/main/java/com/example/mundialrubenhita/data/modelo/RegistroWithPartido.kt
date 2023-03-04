package com.example.mundialrubenhita.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class RegistroWithPartido (
    @Embedded val registro: RegistroEntity,
    @Relation(
        parentColumn = "partidoId",
        entityColumn = "idPartido"
    )
    val partido: PartidoEntity
)