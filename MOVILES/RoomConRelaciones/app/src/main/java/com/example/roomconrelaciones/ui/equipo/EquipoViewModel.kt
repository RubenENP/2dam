package com.example.roomconrelaciones.ui.equipo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomconrelaciones.domain.common.Constantes
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador
import com.example.roomconrelaciones.domain.usescases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquipoViewModel @Inject constructor(
    private val getEquipos: GetEquipos,
    private val addJugadorUseCase: AddJugador,
    private val getJugadoresUseCase: GetJugadores,
    private val borrarJugadorUseCase: DeleteJugador,
    private val updateEquipoUseCase: UpdateEquipo,
): ViewModel() {
    private val _uiState = MutableLiveData(EquipoState())
    val uiState: LiveData<EquipoState> get() = _uiState

    fun handleEvent(event: EquipoEvent){
        when(event){
            is EquipoEvent.GetEquipo -> getEquipo(event.nombre)
            is EquipoEvent.BorrarJugador -> borrarJugador(event.jugador)
            is EquipoEvent.AddJugador -> addJugador(event.jugador, event.equipo)
            is EquipoEvent.UpdateEquipo -> updateEquipo(event.nombre, event.estadio, event.id)
        }
    }

    private fun getEquipo(nombre: String?){
        viewModelScope.launch {
            if (nombre!=null){
                try {
                    val equipo: Equipo = getEquipos.invoke().filter { it.nombre == nombre }[0]

                    getJugadores(equipo)
                } catch (e: Exception){
                    _uiState.value?.copy(error =  e.message)
                }
            } else{
                _uiState.value?.copy(error = Constantes.ERROR)
            }
        }
    }

    private fun borrarJugador(jugador: Jugador){
        viewModelScope.launch {
            try {
                if (_uiState.value?.equipo!=null){
                    val equipo: Equipo = _uiState.value?.equipo!!
                    borrarJugadorUseCase.invoke(jugador, equipo)
                }
            } catch (e: Exception){
                _uiState.value = EquipoState(error = e.message)
            }
        }
    }

    private fun addJugador(jugador: Jugador, equipo: Equipo){
        viewModelScope.launch {
            try {
                addJugadorUseCase.invoke(jugador, equipo)
                getJugadores(equipo)
            } catch (e: Exception){
                _uiState.value = EquipoState(error = e.message)
            }
        }
    }

    private fun getJugadores(equipo: Equipo){
        viewModelScope.launch {
           try {
               val futbolistasList = getJugadoresUseCase.invoke(equipo)

               if (equipo.jugadores.isNotEmpty()){
                   equipo.jugadores.clear()
               }

               equipo.jugadores.addAll(futbolistasList)
               _uiState.value = EquipoState(equipo, null)
           } catch (e: Exception){
               _uiState.value = EquipoState(error = e.message)
           }
        }
    }

    private fun updateEquipo(nombre: String, estadio: String, id: Int?) {
        viewModelScope.launch {
            try {
                id?.let {
                    updateEquipoUseCase.invoke(nombre, estadio, it)
                    getEquipo(_uiState.value?.equipo?.nombre)
                }
            } catch (e: Exception) {
                _uiState.value = EquipoState(error = e.message)
            }
        }
    }
}