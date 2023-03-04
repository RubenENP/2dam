package com.example.relacionnmrubenhita.ui.addJugador

import com.example.relacionnmrubenhita.domain.modelo.Jugador

sealed class AddJugadorEvent {
    class AddJugador(val jugador: Jugador): AddJugadorEvent()

    class AddJugadorDentroDeConsola(val jugador: Jugador, val idConsola: Int): AddJugadorEvent()
}