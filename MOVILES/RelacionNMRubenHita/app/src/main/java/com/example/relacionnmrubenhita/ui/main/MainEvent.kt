package com.example.relacionnmrubenhita.ui.main

import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador

sealed class MainEvent {
    object GetAllConsolas: MainEvent()
    object GetAllJugadores : MainEvent()
    class DeleteConsola(val consola: Consola): MainEvent()
    class DeleteJugador(val jugador: Jugador): MainEvent()
}