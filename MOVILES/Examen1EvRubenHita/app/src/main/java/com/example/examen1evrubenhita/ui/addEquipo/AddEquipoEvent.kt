package com.example.examen1evrubenhita.ui.addEquipo

import com.example.examen1evrubenhita.domain.modelo.Equipo

sealed class AddEquipoEvent {
    class AddEquipo(val equipo: Equipo): AddEquipoEvent()
}