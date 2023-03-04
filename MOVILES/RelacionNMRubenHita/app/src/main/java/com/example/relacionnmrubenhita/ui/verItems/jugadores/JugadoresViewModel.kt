package com.example.relacionnmrubenhita.ui.verItems.jugadores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.usecases.DeleteConsola
import com.example.relacionnmrubenhita.domain.usecases.GetJugadores
import com.example.relacionnmrubenhita.ui.verItems.JugadorAdapter
import com.example.relacionnmrubenhita.ui.verItems.consola.ConsolaState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JugadoresViewModel @Inject constructor(
    private val getUnJugadorUseCase: GetJugadores,
    private val deleteConsolaUseCase: DeleteConsola,
) : ViewModel() {
    private val _uiState = MutableLiveData(JugadoresState())
    val uiState: LiveData<JugadoresState> get() = _uiState

    fun handleEvent(event: JugadoresEvent) {
        when (event) {
            is JugadoresEvent.GetUnJugador -> getUnJugador(event.id)
            is JugadoresEvent.DeleteConsola -> deleteConsola(event.consola)
        }
    }

    private fun getUnJugador(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = JugadoresState(jugador = getUnJugadorUseCase.invoke(id))
            } catch (e: Exception) {
                _uiState.value = JugadoresState(error = e.message)
            }
        }
    }

    private fun deleteConsola(consola: Consola) {
        viewModelScope.launch {
            try {
                deleteConsolaUseCase.invoke(consola)
                _uiState.value?.jugador?.let { getUnJugador(it.id) }
            } catch (e: Exception) {
                _uiState.value = JugadoresState(error = e.message)
            }
        }
    }
}