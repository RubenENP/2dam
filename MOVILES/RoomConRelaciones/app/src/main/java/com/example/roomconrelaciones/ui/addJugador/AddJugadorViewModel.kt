package com.example.roomconrelaciones.ui.addJugador

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador
import com.example.roomconrelaciones.domain.usescases.AddJugador
import com.example.roomconrelaciones.domain.usescases.GetEquipos
import com.example.roomconrelaciones.ui.equipo.EquipoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddJugadorViewModel @Inject constructor(
    private val addJugadorUseCase: AddJugador,
    private val getEquiposUseCase: GetEquipos,
):ViewModel() {
    private val _uiState = MutableLiveData(AddJugadorState())
    val uiState: LiveData<AddJugadorState> get() = _uiState

    fun handleEvent(event: AddJugadorEvent){
        when(event){
            is AddJugadorEvent.Addjugador -> addJugador(event.jugador, event.equipo)
            is AddJugadorEvent.GetEquipo -> getEquipo(event.equipoName)
        }
    }

    private fun getEquipo(nombreEquipo: String){
        viewModelScope.launch {
            try {
                val equipo = getEquiposUseCase.invoke().filter { equipo -> equipo.nombre == nombreEquipo }[0]
                _uiState.value = AddJugadorState(equipo = equipo)
            } catch (e: Exception){
                _uiState.value?.copy(error = e.message)
            }
        }
    }

    private fun addJugador(jugador: Jugador, equipo: Equipo?){
        viewModelScope.launch {
            try {
                if (equipo != null) {
                    addJugadorUseCase.invoke(jugador, equipo)
                }
                _uiState.value = AddJugadorState(equipo=equipo, jugador = jugador)
            } catch (e: Exception){
                _uiState.value?.copy(error = e.message)
            }
        }
    }
}