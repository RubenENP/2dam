package com.example.relacionnmrubenhita.ui.addJugador

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import com.example.relacionnmrubenhita.domain.usecases.AddCrossRef
import com.example.relacionnmrubenhita.domain.usecases.AddJugador
import com.example.relacionnmrubenhita.domain.usecases.GetJugadores
import com.example.relacionnmrubenhita.ui.addConsola.AddConsolaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddJugadorViewModel @Inject constructor(
    private val addJugadorUseCase: AddJugador,
    private val addCrossRefUseCase: AddCrossRef,
    private val getJugadoresUseCase: GetJugadores,
): ViewModel() {
    private val _uiState = MutableLiveData(AddJugadorState())
    val uiState: LiveData<AddJugadorState> get() = _uiState

    fun handleEvent(event: AddJugadorEvent){
        when(event){
            is AddJugadorEvent.AddJugador -> addJugador(event.jugador)
            is AddJugadorEvent.AddJugadorDentroDeConsola -> addJugadorDentroDeConsola(event.jugador, event.idConsola)
        }
    }

    private fun addJugador(jugador: Jugador){
        viewModelScope.launch {
            try {
                addJugadorUseCase.invoke(jugador)
                _uiState.value = AddJugadorState(jugador = jugador)
            } catch (e: Exception){
                _uiState.value = AddJugadorState(error = e.message)
            }
        }
    }

    private fun addJugadorDentroDeConsola(jugador: Jugador, idConsola: Int){
        viewModelScope.launch {
            try {
                addJugadorUseCase.invoke(jugador)
                addCrossRefUseCase.invoke(getJugadoresUseCase.invoke().last().id, idConsola)
                _uiState.value = AddJugadorState(jugador = jugador)
            } catch (e: Exception){
                _uiState.value = AddJugadorState(error = e.message)
            }
        }
    }
}