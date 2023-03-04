package com.example.roomrubenhita.ui.futbolista

import android.util.Log
import androidx.lifecycle.*
import com.airbnb.lottie.utils.LogcatLogger
import com.example.roomrubenhita.domain.modelo.Futbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.AddFutbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.DeleteFutbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.GetFutbolistas
import kotlinx.coroutines.launch
import timber.log.Timber

class FutbolistaViewModel (
    private val addFutbolista: AddFutbolista,
    private val getFutbolistas: GetFutbolistas,
    ) : ViewModel() {

    private val _uiState = MutableLiveData(FutbolistaState())
    val uiState: LiveData<FutbolistaState> get() = _uiState

    fun handleEvent(event: FutbolistaEvent){
        when (event){
            is FutbolistaEvent.AddFutbolista -> addFutbolista(event.futbolista)
            is FutbolistaEvent.GetFutbolista -> getFutbolista(event.nombre)
        }
    }

    private fun getFutbolista(nombre: String){
        viewModelScope.launch {
            val futbolista = getFutbolistas.invoke().find { it.nombre == nombre }!!

            _uiState.value = FutbolistaState(futbolista, null)
        }
    }

    private fun addFutbolista(futbolista: Futbolista){
        viewModelScope.launch() {
            try {
                addFutbolista.invoke(futbolista)
                _uiState.value = FutbolistaState(futbolista, null)
                Timber.tag("TAG:::").e(futbolista.nombre)
            } catch (e: Exception) {
                _uiState.value = FutbolistaState(null, e.message ?: "ERROR no se pudo añadir el jugador")

                Timber.tag("TAG:::").e(e)

                e.message?.let { Log.e(e?.message.toString(), it) }
            }
        }
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class FutbolistaViewModelFactory(
    private val addFutbolista: AddFutbolista,
    private val getFutbolistas: GetFutbolistas,

    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FutbolistaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FutbolistaViewModel(
                addFutbolista,
                getFutbolistas,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

