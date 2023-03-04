package com.example.relacionnmrubenhita.ui.verItems.jugadores

import com.example.relacionnmrubenhita.domain.modelo.Consola

sealed class JugadoresEvent {
    class GetUnJugador(val id: Int): JugadoresEvent()
    class DeleteConsola(val consola: Consola): JugadoresEvent()
}