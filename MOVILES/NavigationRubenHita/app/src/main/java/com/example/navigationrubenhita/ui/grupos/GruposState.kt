package com.example.navigationrubenhita.ui.grupos

import com.example.navigationrubenhita.domain.modelo.Grupo

data class GruposState(
    val allGrupos: MutableList<Grupo>? = null,
    val error: String? = null,
)