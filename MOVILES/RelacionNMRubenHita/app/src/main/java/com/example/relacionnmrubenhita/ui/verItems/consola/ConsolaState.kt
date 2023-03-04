package com.example.relacionnmrubenhita.ui.verItems.consola

import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador

data class ConsolaState (
    val consola: Consola? = null,
    val error: String? = null,
)