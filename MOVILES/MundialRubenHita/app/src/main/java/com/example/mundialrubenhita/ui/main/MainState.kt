package com.example.mundialrubenhita.ui.main

import com.example.mundialrubenhita.domain.modelo.Equipo

data class MainState (val listEquipos: List<Equipo>?=null, val error: String?=null)