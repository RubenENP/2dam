package com.example.relacionnmrubenhita.ui.verItems.jugadores

import com.example.relacionnmrubenhita.domain.modelo.Jugador

data class JugadoresState(
    val jugador: Jugador? = null,
    val error: String? = null,
)