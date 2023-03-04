package com.example.composerubenhita.ui.list

import com.example.composerubenhita.domain.model.Persona
import com.example.composerubenhita.ui.main.MainEvent

data class ListState(val listaPersona: List<Persona> = emptyList(), val error: String? = null)

sealed class ListEvent{
    class LimpiarError : ListEvent()

    object GetAllPersons: ListEvent()
}