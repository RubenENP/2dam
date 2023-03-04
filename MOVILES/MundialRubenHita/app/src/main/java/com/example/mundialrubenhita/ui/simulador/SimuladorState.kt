package com.example.mundialrubenhita.ui.simulador

import com.example.mundialrubenhita.domain.modelo.Equipo
import com.example.mundialrubenhita.domain.modelo.Grupo
import com.example.mundialrubenhita.domain.modelo.PartidoEliminatoria

data class SimuladorState(
    var grupos: List<Grupo>? = null,
    var indexGrupos: Int = 0,
    val error: String? = null,
    val equiposEliminatoria: MutableList<Equipo> = mutableListOf(),
    val partidosEliminatoria: List<PartidoEliminatoria>? = null,
)