package com.example.roomconrelaciones.ui.addEquipo

import com.example.roomconrelaciones.domain.modelo.Equipo

sealed class AddEquipoEvent {
    class AddEquipo(val equipo: Equipo): AddEquipoEvent()
}