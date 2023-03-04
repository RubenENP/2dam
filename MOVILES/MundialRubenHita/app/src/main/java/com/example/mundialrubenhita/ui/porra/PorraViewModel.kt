package com.example.mundialrubenhita.ui.porra

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialrubenhita.domain.useCases.GetDineroUseCase
import com.example.mundialrubenhita.domain.useCases.PartidosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PorraViewModel @Inject constructor(
    private val partidosUseCase: PartidosUseCase,
    private val getDineroUseCase: GetDineroUseCase,
): ViewModel() {
    private val _uiState = MutableLiveData(PorraState())
    val uiState: LiveData<PorraState> get() = _uiState

    fun handleEvent(event: PorraEvent){
        when(event){
            PorraEvent.GetPartidos -> getAllPartidos()
            PorraEvent.ReloadPartidos -> reloadPartidos()
            PorraEvent.GetDinero -> getDinero()
        }
    }

    private fun getDinero() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value?.copy(dinero = getDineroUseCase.getDinero())
            } catch (e: Exception){
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }

    private fun reloadPartidos() {
        viewModelScope.launch {
            try {
                partidosUseCase.generarPartidos()
                getAllPartidos()
            } catch (e: Exception){
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }

    private fun getAllPartidos(){
        viewModelScope.launch {
            try {
                val listaPartidos = partidosUseCase.getPartidosSinJugar()
                _uiState.value = _uiState.value?.copy(partidos = listaPartidos)
            } catch (e: Exception){
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }
}