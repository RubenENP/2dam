package com.example.roomrubenhita.data.modelo

import com.example.roomrubenhita.domain.modelo.Futbolista

fun FutbolistaEntity.toFutbolista(): Futbolista {
    return Futbolista(this.nombre, this.posicion, this.balonesDeOro, this.championsGanadas)
}

fun Futbolista.toFutbolistaEntity(): FutbolistaEntity = FutbolistaEntity(0, this.nombre, this.posicion, this.balonesDeOro, this.championsGanadas)