package com.example.examen1evrubenhita.ui.detalleEquipo

sealed class DetalleEquipoEvent {
    class GetEquipoWithComponentes(val idEquipo: Int): DetalleEquipoEvent()
}