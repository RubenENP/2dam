package com.example.roomconrelaciones.ui.main

import androidx.lifecycle.*
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.usescases.DeleteEquipo
import com.example.roomconrelaciones.domain.usescases.GetEquipos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEquipos: GetEquipos,
    private val deleteEquipo: DeleteEquipo,
): ViewModel() {
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent(event: MainEvent) {
        when(event) {
            MainEvent.GetAllEquipos -> getAllEquipos()
            is MainEvent.DeleteEquipo -> deleteEquipo(event.equipo)
        }
    }

    private fun getAllEquipos() {
        viewModelScope.launch {
            try {
                _uiState.value = MainState(getEquipos.invoke(), null)
            } catch (e: Exception) {
                _uiState.value = MainState(error = e.message)
            }
        }
    }

    private fun deleteEquipo(equipo: Equipo){
        viewModelScope.launch {
            try {
                deleteEquipo.invoke(equipo)

                _uiState.value = MainState(getEquipos.invoke(), null)
            } catch (e: Exception) {
                _uiState.value = MainState(error = e.message)
            }
        }
    }
}