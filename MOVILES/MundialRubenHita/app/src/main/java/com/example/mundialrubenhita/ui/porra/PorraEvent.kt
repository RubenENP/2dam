package com.example.mundialrubenhita.ui.porra

sealed class PorraEvent {
    object GetPartidos : PorraEvent()
    object ReloadPartidos : PorraEvent()
    object GetDinero : PorraEvent()
}