package com.example.examen1evrubenhita.ui.detalleEquipo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen1evrubenhita.domain.usecases.GetEquipos
import com.example.examen1evrubenhita.ui.main.MainEvent
import com.example.examen1evrubenhita.ui.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetallleViewModel @Inject constructor(
    private val getEquipoUseCase: GetEquipos,
): ViewModel() {
    private val _uiState = MutableLiveData(DetalleEquipoState())
    val uiState: LiveData<DetalleEquipoState> get() = _uiState

    fun handleEvent(event: DetalleEquipoEvent) {
        when(event) {
            is DetalleEquipoEvent.GetEquipoWithComponentes -> getEquipo(event.idEquipo)
        }
    }

    private fun getEquipo(idEquipo: Int){
        viewModelScope.launch {
            try {
                _uiState.value = DetalleEquipoState(equipo = getEquipoUseCase.invoke(idEquipo))
            } catch (e: Exception){
                _uiState.value = DetalleEquipoState(error = e.message)
            }
        }
    }
}