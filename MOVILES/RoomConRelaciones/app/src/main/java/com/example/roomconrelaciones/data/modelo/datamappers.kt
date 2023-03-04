package com.example.roomconrelaciones.data.modelo

import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador

fun EquipoEntity.toEquipo(): Equipo {
    return Equipo(this.id, this.nombre, this.estadio, mutableListOf())
}

fun Equipo.toEntity(): EquipoEntity = EquipoEntity(this.id , this.nombre, this.estadio)

fun Jugador.toEntity(equipoId: Int): JugadorEntity = JugadorEntity(0, equipoId, this.nombre, this.posicion, this.titulos)

fun JugadorEntity.toJugador(): Jugador{
    return Jugador(this.nombre, this.posicion, this.titulos)
}
