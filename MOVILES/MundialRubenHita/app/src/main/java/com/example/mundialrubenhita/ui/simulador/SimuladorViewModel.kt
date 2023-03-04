package com.example.mundialrubenhita.ui.simulador

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialrubenhita.domain.useCases.SimuladorUseCase
import com.example.mundialrubenhita.ui.registros.RegistrosState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimuladorViewModel @Inject constructor(
    private val simuladorUseCase: SimuladorUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData(SimuladorState())
    val uiState: LiveData<SimuladorState> get() = _uiState

    fun handleEvent(event: SimuladorEvent) {
        when (event) {
            SimuladorEvent.CrearGrupos -> generarGrupos()
            is SimuladorEvent.DeleteEquipo -> borrarEquipo(event.absoluteAdapterPosition)
            SimuladorEvent.PasarDeGrupo -> pasarDeGrupo()
            SimuladorEvent.EmpezarEliminatoria -> generarPartidosEliminatoria()
        }
    }

    private fun generarPartidosEliminatoria() {
        viewModelScope.launch {
            try {
                _uiState.value?.let {
                    _uiState.value = _uiState.value?.copy(
                        partidosEliminatoria = simuladorUseCase.generarPartidosEliminatoria(it.equiposEliminatoria)
                    )
                }
            } catch (e: Exception){
                _uiState.value = SimuladorState(error = e.message)
            }
        }
    }

    private fun pasarDeGrupo() {
        if (_uiState.value?.indexGrupos!! < 8) {

            _uiState.value!!.equiposEliminatoria.add(
                (_uiState.value!!.grupos?.get(_uiState.value!!.indexGrupos)?.equipos?.get(
                    0
                )!!)
            )
            _uiState.value!!.equiposEliminatoria.add(
                (_uiState.value!!.grupos?.get(_uiState.value!!.indexGrupos)?.equipos?.get(
                    1
                )!!)
            )

            if (_uiState.value!!.indexGrupos <7){
                _uiState.value!!.indexGrupos++
            } else{
                _uiState.value!!.grupos=null
            }
        } else {
            _uiState.value!!.grupos = null
        }
    }

    private fun generarGrupos() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value?.copy(grupos = simuladorUseCase.generarGrupos())

            } catch (e: Exception) {
                _uiState.value = SimuladorState(error = e.message)
            }
        }
    }

    private fun borrarEquipo(index: Int) {
        viewModelScope.launch {
            try {
                _uiState.value?.grupos?.get(_uiState.value!!.indexGrupos)?.equipos?.removeAt(index)
            } catch (e: Exception) {
                _uiState.value = SimuladorState(error = e.message)
            }
        }
    }
}