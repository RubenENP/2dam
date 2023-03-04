package com.example.mundialrubenhita.ui.simulador

sealed class SimuladorEvent {
    object CrearGrupos : SimuladorEvent()
    object PasarDeGrupo : SimuladorEvent()
    object EmpezarEliminatoria : SimuladorEvent()
    class DeleteEquipo(val absoluteAdapterPosition: Int) : SimuladorEvent()
}