package com.example.examen1evrubenhita.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class EquipoWithComponentes(
    @Embedded val equipoEntity: EquipoEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idEquipo"
    )
    val componenteList: List<ComponenteEntity>?
)