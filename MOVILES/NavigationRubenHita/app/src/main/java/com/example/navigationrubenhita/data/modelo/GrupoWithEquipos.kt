package com.example.navigationrubenhita.data.modelo

import androidx.room.Embedded
import androidx.room.Relation

data class GrupoWithEquipos(
    @Embedded val grupo: GrupoEntity,
    @Relation(
        parentColumn = "idEquipo1",
        entityColumn = "idEquipo"
    )
    val equipo1: EquipoEntity,

    @Relation(
        parentColumn = "idEquipo2",
        entityColumn = "idEquipo"
    )
    val equipo2: EquipoEntity,

    @Relation(
        parentColumn = "idEquipo3",
        entityColumn = "idEquipo"
    )
    val equipo3: EquipoEntity,

    @Relation(
        parentColumn = "idEquipo4",
        entityColumn = "idEquipo"
    )
    val equipo4: EquipoEntity
)