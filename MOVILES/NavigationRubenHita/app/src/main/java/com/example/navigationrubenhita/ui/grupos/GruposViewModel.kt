package com.example.navigationrubenhita.ui.grupos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationrubenhita.domain.modelo.Grupo
import com.example.navigationrubenhita.domain.useCases.DeleteGrupo
import com.example.navigationrubenhita.domain.useCases.GetGrupos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GruposViewModel @Inject constructor(
    private val getGruposUseCase: GetGrupos,
    private val deleteGrupoUseCase: DeleteGrupo,
): ViewModel() {
    private val _uiState = MutableLiveData(GruposState())
    val uiState: LiveData<GruposState> get() = _uiState

    fun handleEvent(event: GruposEvent){
        when(event){
            GruposEvent.GenerarGrupos -> generarGrupos()
            is GruposEvent.DeleteGrupo -> deleteGrupo(event.i)
        }
    }

    private fun generarGrupos(){
        viewModelScope.launch {
            try {
                val gruposList: List<Grupo> = getGruposUseCase.invoke()
                _uiState.value = GruposState(allGrupos = gruposList.toMutableList())
            } catch (e: Exception){
                _uiState.value = GruposState(error = e.message)
            }
        }
    }


    private fun deleteGrupo(i: Int){
        viewModelScope.launch {
            try {
                val grupo = _uiState.value?.allGrupos?.filter { grupo -> grupo.id==i }?.get(0)
                _uiState.value?.allGrupos?.remove(grupo)
            } catch (e: Exception){
                _uiState.value = GruposState(error = e.message)
            }
        }
    }
}