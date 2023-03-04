package com.example.relacionnmrubenhita.ui.addConsola

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.usecases.AddConsola
import com.example.relacionnmrubenhita.domain.usecases.AddCrossRef
import com.example.relacionnmrubenhita.domain.usecases.GetConsolas
import com.example.relacionnmrubenhita.domain.usecases.GetJugadores
import com.example.relacionnmrubenhita.ui.addJugador.AddJugadorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddConsolaViewModel @Inject constructor(
    private val addConsolaUseCase: AddConsola,
    private val addCrossRefUseCase: AddCrossRef,
    private val getConsolasUseCase: GetConsolas,
): ViewModel() {
    private val _uiState = MutableLiveData(AddConsolaState())
    val uiState: LiveData<AddConsolaState> get() = _uiState

    fun handleEvent(event: AddConsolaEvent){
        when(event){
            is AddConsolaEvent.AddConsola -> addConsola(event.consola)
            is AddConsolaEvent.AddConsolaDentroDeJugador -> addConsolaDentroDeJugador(event.consola, event.idJugador)
        }
    }

    private fun addConsola(consola: Consola){
        viewModelScope.launch {
            try {
                addConsolaUseCase.invoke(consola)
                _uiState.value = AddConsolaState(consola = consola)
            } catch (e: Exception){
                _uiState.value = AddConsolaState(error = e.message)
            }
        }
    }

    private fun addConsolaDentroDeJugador(consola: Consola, idJugador: Int){
        viewModelScope.launch {
            try {
                addConsolaUseCase.invoke(consola)
                addCrossRefUseCase.invoke(idJugador, getConsolasUseCase.invoke().last().id)
                _uiState.value = AddConsolaState(consola = consola)
            } catch (e: Exception){
                _uiState.value = AddConsolaState(error = e.message)
            }
        }
    }
}