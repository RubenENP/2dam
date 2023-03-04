package com.example.navigationrubenhita.ui.partidos

sealed class PartidosEvent {
    object GetPartidos : PartidosEvent()
    object GetGrupos : PartidosEvent()
}