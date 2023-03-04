package com.example.relacionnmrubenhita.ui.addConsola

import com.example.relacionnmrubenhita.domain.modelo.Consola

data class AddConsolaState(
    val consola: Consola? = null,
    val error: String? = null,
)
