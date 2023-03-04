package com.example.examen1evrubenhita.ui.main

import com.example.examen1evrubenhita.domain.modelo.Equipo

sealed class MainEvent {
    object GetEquipos: MainEvent()
    class BorrarEquipo(val equipo: Equipo): MainEvent()
}
