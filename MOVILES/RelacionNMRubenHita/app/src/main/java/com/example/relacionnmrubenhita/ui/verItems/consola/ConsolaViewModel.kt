package com.example.relacionnmrubenhita.ui.verItems.consola

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import com.example.relacionnmrubenhita.domain.usecases.DeleteJugador
import com.example.relacionnmrubenhita.domain.usecases.GetConsolas
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsolaViewModel @Inject constructor(
    private val getConsolaUseCase: GetConsolas,
    private val deleteJugadorUseCase: DeleteJugador,
) : ViewModel() {
    private val _uiState = MutableLiveData(ConsolaState())
    val uiState: LiveData<ConsolaState> get() = _uiState

    fun handleEvent(event: ConsolaEvent) {
        when(event){
            is ConsolaEvent.GetUnaConsola -> getUnaConsola(event.idConsola)
            is ConsolaEvent.BorrarJugador -> borrarJugador(event.jugador)
        }
    }

    private fun getUnaConsola(id: Int){
        viewModelScope.launch {
            try {
                _uiState.value = ConsolaState(consola = getConsolaUseCase.invoke(id))
            } catch (e: Exception){
                _uiState.value = ConsolaState(error = e.message)
            }
        }
    }

    private fun borrarJugador(jugador: Jugador){
        viewModelScope.launch {
            try {
                deleteJugadorUseCase.invoke(jugador)
                _uiState.value?.consola?.id?.let { getUnaConsola(it) }
            } catch (e: Exception) {
                _uiState.value = ConsolaState(error = e.message)
            }
        }
    }
}