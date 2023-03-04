package com.example.relacionnmrubenhita.data.modelo

import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador

fun Consola.toEntity(): ConsolaEntity = ConsolaEntity(this.id, this.nombre, this.marca, this.precio)

fun ConsolaEntity.toConsola(): Consola{
    return Consola(this.consolaId, this.nombre, this.modelo, this.precio, emptyList())
}

fun JugadorEntity.toJugador(): Jugador {
    return Jugador(this.jugadorId, this.nombre, this.dinero, emptyList())
}

fun Jugador.toEntity(): JugadorEntity = JugadorEntity(this.id, this.nombre, this.dinero)

fun Consola.toConsolaWithJugadores(): ConsolaWithJugadores = ConsolaWithJugadores(
    ConsolaEntity(this.id, this.nombre, this.marca, this.precio), this.jugadoresList.map { it.toEntity() })

fun ConsolaWithJugadores.toConsola(): Consola{
    return Consola(consola.toConsola().id, consola.toConsola().nombre,
        consola.toConsola().marca, consola.toConsola().precio, jugadores.map { it.toJugador() })
}

fun JugadorWithConsolas.toJugador(): Jugador {
    return Jugador(jugador.toJugador().id, jugador.toJugador().nombre, jugador.toJugador().dinero, consolas.map { it.toConsola() })
}

fun Jugador.toJugadorWithConsolas(): JugadorWithConsolas = JugadorWithConsolas(JugadorEntity(this.id, this.nombre, this.dinero),
    this.consolasList.map { it.toEntity() })