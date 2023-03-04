package com.example.futbolistasrubenhita1.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.AddFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.GetFutbolistas

class MainViewModel(
    private val addFutbolistaUseCase: AddFutbolista,
    private val getFutbolistas: GetFutbolistas,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun addFutbolista(futbolista: Futbolista) {
        if(getFutbolistas.invoke().any { f -> f.nombre != futbolista.nombre }){
            if (addFutbolistaUseCase(futbolista)) {
                _uiState.value = MainState(futbolista, null)
            } else {
                _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
            }
        } else {
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR + Constantes.EL_FUTBOLISTA_YA_EXISTE)
        }
    }

    fun getAllFutbolistas() = getFutbolistas.invoke()

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val addFutbolista: AddFutbolista,
    private val getFutbolistas: GetFutbolistas,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addFutbolista,
                getFutbolistas,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}