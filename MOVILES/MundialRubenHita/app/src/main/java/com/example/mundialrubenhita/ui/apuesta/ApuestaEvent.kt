package com.example.mundialrubenhita.ui.apuesta

sealed class ApuestaEvent {
    class GetPartido(val idPartido: Int) : ApuestaEvent()
    class Apostar(val cantidad: Int, val apuesta: Int) : ApuestaEvent()
}