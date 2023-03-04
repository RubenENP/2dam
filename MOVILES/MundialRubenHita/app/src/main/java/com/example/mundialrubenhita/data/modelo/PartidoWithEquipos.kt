package com.example.mundialrubenhita.data.modelo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PartidoWithEquipos(
    @Embedded val partido: PartidoEntity,
    @Relation(
        parentColumn = "idPartido",
        entityColumn = "idEquipo",
        associateBy = Junction(PartidoEquipoCrossRef::class)
    )
    val equipos: List<EquipoEntity>
)