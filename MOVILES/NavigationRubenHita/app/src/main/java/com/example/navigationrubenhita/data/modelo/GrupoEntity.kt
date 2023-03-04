package com.example.navigationrubenhita.data.modelo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "grupos",
foreignKeys = [
    ForeignKey(entity = EquipoEntity::class,
        parentColumns = ["idEquipo"],
        childColumns = ["idEquipo1"]),
    ForeignKey(entity = EquipoEntity::class,
        parentColumns = ["idEquipo"],
        childColumns = ["idEquipo2"]),
    ForeignKey(entity = EquipoEntity::class,
        parentColumns = ["idEquipo"],
        childColumns = ["idEquipo3"]),
    ForeignKey(entity = EquipoEntity::class,
        parentColumns = ["idEquipo"],
        childColumns = ["idEquipo4"])
])
data class GrupoEntity(
    @PrimaryKey(autoGenerate = false)
    val idGrupo: Int,

    val idEquipo1: Int?,
    val idEquipo2: Int?,
    val idEquipo3: Int?,
    val idEquipo4: Int?,
)