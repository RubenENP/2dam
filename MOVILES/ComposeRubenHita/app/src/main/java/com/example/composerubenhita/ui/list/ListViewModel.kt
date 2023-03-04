package com.example.composerubenhita.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composerubenhita.domain.useCase.GetPersonsUseCase
import com.example.composerubenhita.ui.list.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPersonsUseCase: GetPersonsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(ListState())
    val state = _state.asStateFlow()

    fun handleEvent(event: ListEvent){
        when(event){
            is ListEvent.GetAllPersons -> getAllPersons()
            is ListEvent.LimpiarError -> limpiarError()
        }
    }

    private fun limpiarError() {
        _state.update {
            _state.value.copy(
                error = null
            )
        }
    }

    private fun getAllPersons() {
        viewModelScope.launch {
            try {
                _state.update {
                    _state.value.copy(listaPersona = getPersonsUseCase.invoke())
                }
            } catch (e: Exception){
                _state.update {
                    _state.value.copy(error = e.message)
                }
            }
        }
    }
}