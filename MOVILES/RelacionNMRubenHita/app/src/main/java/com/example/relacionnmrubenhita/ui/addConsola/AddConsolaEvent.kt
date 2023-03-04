package com.example.relacionnmrubenhita.ui.addConsola

import com.example.relacionnmrubenhita.domain.modelo.Consola

sealed class AddConsolaEvent {
    class AddConsola(val consola: Consola): AddConsolaEvent()
    class AddConsolaDentroDeJugador(val consola: Consola, val idJugador: Int): AddConsolaEvent()
}