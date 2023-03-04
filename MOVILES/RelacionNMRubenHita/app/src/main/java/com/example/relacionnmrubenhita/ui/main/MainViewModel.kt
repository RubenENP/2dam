package com.example.relacionnmrubenhita.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import com.example.relacionnmrubenhita.domain.usecases.DeleteConsola
import com.example.relacionnmrubenhita.domain.usecases.DeleteJugador
import com.example.relacionnmrubenhita.domain.usecases.GetConsolas
import com.example.relacionnmrubenhita.domain.usecases.GetJugadores
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getConsolasUseCase: GetConsolas,
    private val deleteConsolaUseCase: DeleteConsola,
    private val getJugadoresUseCase: GetJugadores,
    private val deleteJugadorUseCase: DeleteJugador,
): ViewModel(){
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent(event: MainEvent) {
        when(event) {
            MainEvent.GetAllConsolas -> getAllConsolas()
            is MainEvent.DeleteConsola -> deleteConsola(event.consola)
            MainEvent.GetAllJugadores -> getAllJugadores()
            is MainEvent.DeleteJugador -> deleteJugador(event.jugador)
        }
    }

    private fun deleteConsola(consola: Consola) {
        viewModelScope.launch {
            try {
                deleteConsolaUseCase.invoke(consola)
                getAllConsolas()
            } catch (e: Exception){
                _uiState.value = MainState(error = e.message)
            }
        }
    }

    private fun getAllConsolas(){
        viewModelScope.launch {
            try {
                _uiState.value = MainState(consolasList = getConsolasUseCase.invoke())
            } catch (e: Exception){
                _uiState.value = MainState(error = e.message)
            }
        }
    }

    private fun getAllJugadores() {
        viewModelScope.launch {
            try {
                _uiState.value = MainState(jugadoresList = getJugadoresUseCase.invoke())
            } catch (e: Exception){
                _uiState.value = MainState(error = e.message)
            }
        }
    }

    private fun deleteJugador(jugador: Jugador) {
        viewModelScope.launch {
            try {
                deleteJugadorUseCase.invoke(jugador)
                getAllJugadores()
            } catch (e: Exception){
                _uiState.value = MainState(error = e.message)
            }
        }
    }
}