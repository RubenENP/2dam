package com.example.relacionnmrubenhita.ui.main

import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador

data class MainState (
    val consolasList: List<Consola>? = null,
    val jugadoresList: List<Jugador>? = null,
    val error: String? = null,
)