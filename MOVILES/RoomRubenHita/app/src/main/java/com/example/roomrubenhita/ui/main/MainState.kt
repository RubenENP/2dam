package com.example.roomrubenhita.ui.main

import com.example.roomrubenhita.domain.modelo.Futbolista

data class MainState (
    var listFutbolistas: List<Futbolista> = emptyList(),
    val error: String? = null
)