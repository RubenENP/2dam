package com.example.examen1evrubenhita.data.modelo

import com.example.examen1evrubenhita.domain.modelo.Componente
import com.example.examen1evrubenhita.domain.modelo.Equipo

fun EquipoEntity.toEquipo() :Equipo {
    return Equipo(this.id, this.nombre, this.nacionalidad, this.puesto, emptyList())
}

fun Equipo.toEntity() = EquipoEntity(this.id, this.nombre, this.nacionalidad, this.puesto)

fun EquipoWithComponentes.toEquipo(): Equipo{
    return Equipo(this.equipoEntity.id, this.equipoEntity.nombre, this.equipoEntity.nacionalidad,this.equipoEntity.puesto,
        this.componenteList?.map { it.toComponente() })
}

fun ComponenteEntity.toComponente(): Componente{
    return Componente(this.id, this.tipo, this.nombre)
}