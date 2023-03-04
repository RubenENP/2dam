package com.example.futbolistasrubenhita1.ui.pantallaMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.AddFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.DeleteFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.GetFutbolistas
import com.example.futbolistasrubenhita1.utils.StringProvider

class MainViewModel(
    private val stringProvider: StringProvider,
    private val addFutbolistaUseCase: AddFutbolista,
    private val getFutbolistas: GetFutbolistas,
    private val deleteFutbolista: DeleteFutbolista,
) : ViewModel() {

    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun addFutbolista(futbolista: Futbolista) {
        if(getAllFutbolistas().any { f -> f.nombre != futbolista.nombre }){
            if (addFutbolistaUseCase(futbolista)) {
                _uiState.value = MainState(futbolista, null)
            } else {
                _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
            }
        } else {
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR + Constantes.EL_FUTBOLISTA_YA_EXISTE)
        }
    }

    fun getAllFutbolistas(): List<Futbolista> {
        return getFutbolistas.invoke()
    }

    fun getFutbolistas(id: Int): String {
        val futbolistas = getFutbolistas()

        if (futbolistas.size < id || id < 0) {
            _uiState.value = _uiState.value?.copy(error = Constantes.ERROR)
        } else {
            _uiState.value = futbolistas[id].let { _uiState.value?.copy(futbolista = it) }
        }

        return _uiState.value?.futbolista.toString()
    }

    fun deleteFutbolista(id: Int) {
        deleteFutbolista.invoke(id)
    }

    fun getFutbolistasSize(): Int {
        return getFutbolistas.invoke().size
    }

    fun errorMostrado() {
        _uiState.value = _uiState.value?.copy(error = null)
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class MainViewModelFactory(
    private val stringProvider: StringProvider,
    private val addFutbolista: AddFutbolista,
    private val getFutbolistas: GetFutbolistas,
    private val deleteFutbolista: DeleteFutbolista,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                stringProvider,
                addFutbolista,
                getFutbolistas,
                deleteFutbolista,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}