package com.example.navigationrubenhita.ui.partidos

import com.example.navigationrubenhita.domain.modelo.Partido

data class PartidosState (
    val partidos: List<Partido>? = null,
    val gruposSize:Int = 0,
    val error: String? = null,
)