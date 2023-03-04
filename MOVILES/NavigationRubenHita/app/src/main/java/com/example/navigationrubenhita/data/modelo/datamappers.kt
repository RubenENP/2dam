package com.example.navigationrubenhita.data.modelo

import com.example.navigationrubenhita.domain.modelo.Equipo
import com.example.navigationrubenhita.domain.modelo.Grupo

fun EquipoEntity.toEquipo(): Equipo {
    return Equipo(this.idEquipo, this.nombre, this.escudoImg)
}

fun Grupo.toGrupoEntity() = GrupoEntity(this.id, this.equipos[0].id, this.equipos[1].id, this.equipos[2].id, this.equipos[3].id)

fun GrupoWithEquipos.toGrupo(): Grupo {
    return Grupo(
        this.grupo.idGrupo,
        mutableListOf(
            this.equipo1.toEquipo(),
            this.equipo2.toEquipo(),
            this.equipo3.toEquipo(),
            this.equipo4.toEquipo(),
        )
    )
}

fun Equipo.toEntity() = EquipoEntity(this.id, this.nombre, this.escudoImg)