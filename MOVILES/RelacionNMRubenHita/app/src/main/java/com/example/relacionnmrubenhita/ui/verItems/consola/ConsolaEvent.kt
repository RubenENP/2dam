package com.example.relacionnmrubenhita.ui.verItems.consola

import com.example.relacionnmrubenhita.domain.modelo.Jugador

sealed class ConsolaEvent {
    class GetUnaConsola(val idConsola: Int): ConsolaEvent()
    class BorrarJugador(val jugador: Jugador) : ConsolaEvent()
}