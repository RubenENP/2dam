package com.example.composerubenhita.ui.main


data class MainState(
    val persona: Persona = Persona(),
    val error: String? = null,
    val isLoading: Boolean = false,
)

data class Persona(
    var nombre: String="",
    var apellido: String="",
)

sealed class MainEvent(){
    class AddPersona(val nombre: String, val apellido: String) : MainEvent()
    class LimpiarError : MainEvent()
    class SetNombre(val nombre: String) : MainEvent()
    class SetApellido(val apellido: String) : MainEvent()
}
