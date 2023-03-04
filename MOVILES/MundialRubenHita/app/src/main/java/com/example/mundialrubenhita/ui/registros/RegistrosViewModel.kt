package com.example.mundialrubenhita.ui.registros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialrubenhita.domain.useCases.GetRegistrosUseCase
import com.example.mundialrubenhita.ui.porra.PorraState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrosViewModel @Inject constructor(private val getRegistrosUseCase: GetRegistrosUseCase) : ViewModel(){
    private val _uiState = MutableLiveData(RegistrosState())
    val uiState: LiveData<RegistrosState> get() = _uiState

    fun handleEvent(event: RegistrosEvent){
        when(event){
            RegistrosEvent.GetRegistros -> getRegistros()
        }
    }

    private fun getRegistros(){
        viewModelScope.launch {
            try {
                _uiState.value = RegistrosState(listaRegistros = getRegistrosUseCase.invoke())
            } catch (e: Exception){
                _uiState.value = RegistrosState(error = e.message)
            }
        }
    }
}