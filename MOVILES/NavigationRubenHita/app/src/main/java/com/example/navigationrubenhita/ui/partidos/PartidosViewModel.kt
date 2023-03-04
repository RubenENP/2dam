package com.example.navigationrubenhita.ui.partidos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationrubenhita.domain.useCases.GetGrupos
import com.example.navigationrubenhita.domain.useCases.GetPartidos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartidosViewModel @Inject constructor (
    private val getPartidosUseCase: GetPartidos,
    private val getGruposUseCase: GetGrupos,
) : ViewModel() {
    private val _uiState = MutableLiveData(PartidosState())
    val uiState: LiveData<PartidosState> get() = _uiState

    fun handleEvent(event: PartidosEvent){
        when(event){
            PartidosEvent.GetPartidos -> getPartidos()
            PartidosEvent.GetGrupos -> getGrupos()
        }
    }

    private fun getPartidos(){
        try {
            _uiState.value = PartidosState(partidos = getPartidosUseCase.invoke())
        } catch (e: Exception){
            _uiState.value = PartidosState(error = e.message)
        }
    }

    private fun getGrupos(){
        viewModelScope.launch {
            try {
                _uiState.value = PartidosState(gruposSize = getGruposUseCase.getAllGrupos())
            } catch (e: Exception){
                _uiState.value = PartidosState(error = e.message)
            }
        }
    }
}