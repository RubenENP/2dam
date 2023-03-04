package com.example.navigationrubenhita.ui.unGrupo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationrubenhita.domain.useCases.GetGrupos
import com.example.navigationrubenhita.domain.useCases.UpdateGrupo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartidosDeUnGrupoViewModel @Inject constructor(
    private val getGruposUseCase: GetGrupos,
    private val updateGrupoUseCase: UpdateGrupo,
) : ViewModel() {
    private val _uiState = MutableLiveData(PartidosDeUnGrupoState())
    val uiState: LiveData<PartidosDeUnGrupoState> get() = _uiState

    fun handleEvent(event: PartidoDeUnGrupoEvent) {
        when (event) {
            is PartidoDeUnGrupoEvent.SetGrupo -> setGrupo(event.id)
            is PartidoDeUnGrupoEvent.DeleteEquipo -> deleteEquipo(event.i)
            PartidoDeUnGrupoEvent.SetGrupoGanadores -> setGrupoConGanadores()
        }
    }

    private fun setGrupo(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = PartidosDeUnGrupoState(grupo = getGruposUseCase.getUnGrupo(id))
            } catch (e: Exception) {
                _uiState.value = PartidosDeUnGrupoState(error = e.message)
            }
        }
    }

    private fun deleteEquipo(i: Int) {
        viewModelScope.launch {
            try {
                val equipo = _uiState.value?.grupo?.equipos?.get(i)
                if (equipo != null){
                    _uiState.value?.grupo?.equipos?.remove(equipo)
                }
            } catch (e: Exception){
                _uiState.value = PartidosDeUnGrupoState(error = e.message)
            }

        }
    }

    private fun setGrupoConGanadores(){
        viewModelScope.launch {
            try {
                val grupo = _uiState.value?.grupo
                if (grupo != null) {
                    updateGrupoUseCase.invoke(grupo)
                }
            } catch (e: Exception){
                _uiState.value = PartidosDeUnGrupoState(error = e.message)
            }
        }
    }
}