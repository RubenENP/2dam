package com.example.navigationrubenhita.ui.grupos

sealed class GruposEvent {
    object GenerarGrupos : GruposEvent()
    class DeleteGrupo(val i: Int) : GruposEvent()
}