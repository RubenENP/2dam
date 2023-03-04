package com.example.composerubenhita.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composerubenhita.domain.model.Persona
import com.example.composerubenhita.domain.useCase.AddPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val addPersonUseCase: AddPersonUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun handleEvent(event: MainEvent){
        when(event){
            is MainEvent.AddPersona -> addPersona(event.nombre, event.apellido)
            is MainEvent.LimpiarError -> limpiaError()
            is MainEvent.SetNombre -> setNombre(event.nombre)
            is MainEvent.SetApellido -> setApellido(event.apellido)
        }
    }

    private fun setApellido(apellido: String) {
        _state.value.persona.apellido = apellido
    }

    private fun setNombre(nombre: String) {
        _state.value.persona.nombre = nombre
    }

    private fun addPersona(nombre: String, apellido: String){
        viewModelScope.launch {
            try {
                addPersonUseCase.invoke(Persona(nombre, apellido))
            } catch (e:Exception){
                e.message?.let { Log.e("TAG:::", it) }
                _state.update { it.copy(error = e.message) }
            }
        }
    }

    private fun limpiaError() {
        _state.update {
            _state.value.copy(
                error = null
            )
        }
    }
}