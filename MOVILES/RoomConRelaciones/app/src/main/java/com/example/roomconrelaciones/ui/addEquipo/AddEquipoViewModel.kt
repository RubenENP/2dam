package com.example.roomconrelaciones.ui.addEquipo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.usescases.AddEquipo
import com.example.roomconrelaciones.ui.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEquipoViewModel @Inject constructor(private val addEquipoUseCase: AddEquipo,):ViewModel() {
    private val _uiState = MutableLiveData(AddEquipoState())
    val uiState: LiveData<AddEquipoState> get() = _uiState

    fun handleEvent(event: AddEquipoEvent){
        when(event){
            is AddEquipoEvent.AddEquipo -> addEquipo(event.equipo)
        }
    }

    private fun addEquipo(equipo: Equipo){
        viewModelScope.launch {
            try {
                addEquipoUseCase(equipo)
                _uiState.value = AddEquipoState(equipo,null)
            } catch (e: Exception){
                _uiState.value = AddEquipoState(error = e.message)
            }
        }
    }
}