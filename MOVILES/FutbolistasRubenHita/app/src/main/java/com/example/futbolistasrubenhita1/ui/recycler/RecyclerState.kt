package com.example.futbolistasrubenhita1.ui.recycler

import com.example.futbolistasrubenhita1.domain.modelo.Futbolista

data class RecyclerState(
    val listFutbolistas: List<Futbolista> = emptyList(),
    val error: String? = null
)