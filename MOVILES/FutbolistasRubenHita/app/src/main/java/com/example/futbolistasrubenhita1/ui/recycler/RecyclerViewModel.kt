package com.example.futbolistasrubenhita1.ui.recycler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.DeleteFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.GetFutbolistas
import timber.log.Timber

class RecyclerViewModel(
    private val deleteFutbolista: DeleteFutbolista,
    private val getFutbolistas: GetFutbolistas,
) : ViewModel() {

    private val _uiState = MutableLiveData(RecyclerState())
    val uiState: LiveData<RecyclerState> get() = _uiState

    init {
        getAllFutbolistas()
    }

    fun deleteFutbolista(futbolista: Futbolista) {
        val id: Int = getFutbolistas.invoke().indexOf(futbolista)

        if (!deleteFutbolista.invoke(id)){
            _uiState.value?.copy(error = "error jefe")
        } else {
            _uiState.value = RecyclerState(getFutbolistas.invoke(), null)
        }
    }

    fun errorMostrado() {
        _uiState.value?.copy(error = null)
    }

    fun getAllFutbolistas() {
        if (getFutbolistas.invoke().isEmpty()) {
            _uiState.value = RecyclerState(error = "No hay nada jefe")
            Timber.i("No hay nada jefe")
        } else{
            _uiState.value = RecyclerState(listFutbolistas = getFutbolistas.invoke())
        }
    }
}


/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class RecyclerViewModelFactory(
    private val deleteFutbolista: DeleteFutbolista,
    private val getFutbolistas: GetFutbolistas,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecyclerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecyclerViewModel(deleteFutbolista, getFutbolistas) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}