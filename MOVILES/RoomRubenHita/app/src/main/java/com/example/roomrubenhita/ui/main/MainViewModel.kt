package com.example.roomrubenhita.ui.main

import androidx.lifecycle.*
import com.example.roomrubenhita.domain.modelo.Futbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.DeleteFutbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.GetFutbolistas
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val getFutbolistas: GetFutbolistas,
    private val deleteFutbolista: DeleteFutbolista,
) : ViewModel() {
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    init {
        getAllFutbolistas()
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetAllFutbolistas -> getAllFutbolistas()
            is MainEvent.DeleteFutbolista -> deleteFutbolista(event.futbolista)
        }
    }

    private fun deleteFutbolista(futbolista: Futbolista) {
        viewModelScope.launch {
            try {
                deleteFutbolista.invoke(futbolista)
                _uiState.value = MainState(getFutbolistas.invoke(), null)
            } catch (e: Exception) {
                _uiState.value = MainState(error = e.message)
            }
        }
    }

    private fun getAllFutbolistas() {
        viewModelScope.launch {
            try {
                _uiState.value = MainState(getFutbolistas.invoke(), null)
            } catch (e: Exception) {
                _uiState.value = MainState(error = e.message)
            }
        }
    }

    /**
     * Factory class to instantiate the [ViewModel] instance.
     */
    class MainViewModelFactory(
        private val getFutbolistas: GetFutbolistas,
        private val deleteFutbolista: DeleteFutbolista,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(
                    getFutbolistas,
                    deleteFutbolista
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}