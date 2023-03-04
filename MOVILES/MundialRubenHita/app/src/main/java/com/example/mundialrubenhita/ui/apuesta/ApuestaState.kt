package com.example.mundialrubenhita.ui.apuesta

import com.example.mundialrubenhita.domain.modelo.Apuesta
import com.example.mundialrubenhita.domain.modelo.Partido

data class ApuestaState(
    var partido: Partido? = null,
    val dinero: Double? = null,
    val error: String? = null,
    val precioLocal: Double? = null,
    val precioVisitante: Double? = null,
    val precioEmpate: Double? = null,
    val apuestaModel: Apuesta? = null,
)