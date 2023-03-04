package com.example.examen1evrubenhita.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen1evrubenhita.domain.modelo.Equipo
import com.example.examen1evrubenhita.domain.usecases.DeleteEquipo
import com.example.examen1evrubenhita.domain.usecases.GetEquipos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getEquiposUseCase: GetEquipos,
    private val deleteEquipoUseCase: DeleteEquipo,
) :ViewModel() {
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent(event: MainEvent) {
        when(event) {
            MainEvent.GetEquipos -> getAllEquipos()
            is MainEvent.BorrarEquipo -> deleteEquipo(event.equipo)
        }
    }

    private fun getAllEquipos(){
        viewModelScope.launch {
            try {
                _uiState.value = MainState(equiposList = getEquiposUseCase.invoke())
            } catch (e: Exception){
                _uiState.value = MainState(error = e.message)
            }
        }
    }

    private fun deleteEquipo(equipo: Equipo){
        viewModelScope.launch {
            try {
                deleteEquipoUseCase.invoke(equipo)
                getAllEquipos()
            } catch (e: Exception){
                _uiState.value = MainState(error = e.message)
            }
        }
    }
}