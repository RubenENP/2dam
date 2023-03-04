package com.example.mundialrubenhita.data.modelo

import com.example.mundialrubenhita.domain.modelo.Equipo
import com.example.mundialrubenhita.domain.modelo.Partido
import com.example.mundialrubenhita.domain.modelo.Registro

fun EquipoEntity.toEquipo(): Equipo {
    return Equipo(this.idEquipo, this.nombre, this.escudoImg, this.alineacionImg, this.rankingFifa)
}

fun PartidoWithEquipos.toPartido(): Partido {
    return Partido(
        this.partido.idPartido,
        this.equipos[0].toEquipo(),
        this.equipos[1].toEquipo(),
        this.partido.golesLocal,
        this.partido.golesVisitante,
        this.partido.resultado
    )
}

fun RegistroWithPartido.toRegistro(): Registro {
    return Registro(this.registro.id, this.registro.balance, this.registro.dinero, this.partido.idPartido, null)
}

fun Registro.toRegistroEntity() = RegistroEntity(this.id,this.balance, this.dinero, this.idPartido)

fun Partido.toPartidoEntity() = PartidoEntity(this.idPartido, this.golesLocal, this.golesVisitante, this.resultado)