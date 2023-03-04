package com.example.relacionnmrubenhita.ui.addJugador

import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador

data class AddJugadorState (
    val jugador: Jugador? = null,
    val error: String? = null,
)