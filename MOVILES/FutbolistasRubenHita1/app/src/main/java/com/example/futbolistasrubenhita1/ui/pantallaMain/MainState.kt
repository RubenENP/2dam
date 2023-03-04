package com.example.futbolistasrubenhita1.ui.pantallaMain

import com.example.futbolistasrubenhita1.domain.modelo.Futbolista

data class MainState (
    val futbolista: Futbolista = Futbolista("El Bicho", "Delantero", 5, 5),
    val error: String? = ""
)