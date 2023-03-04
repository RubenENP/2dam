package com.example.ProbandoCosas.ui.pantallaMain

import com.example.ProbandoCosas.domain.modelo.Futbolista

data class MainState (
    val futbolista: Futbolista = Futbolista("El Bicho", "Delantero", 5, 5),
    val error: String? = ""
)