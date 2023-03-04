package com.example.mundialrubenhita.ui.porra

import com.example.mundialrubenhita.domain.modelo.Partido

data class PorraState(
    var partidos: List<Partido>? = null,
    var dinero: Double? = null,
    var error: String? = null,
)