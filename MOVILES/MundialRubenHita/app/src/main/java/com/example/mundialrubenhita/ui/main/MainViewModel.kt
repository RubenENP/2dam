package com.example.mundialrubenhita.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialrubenhita.domain.useCases.EquiposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val equiposUseCase: EquiposUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData(MainState())
    val uiState: LiveData<MainState> get() = _uiState

    fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.GetEquipos -> getAllEquipos()
        }
    }

    private fun getAllEquipos() {
        viewModelScope.launch {
            try {
                _uiState.value = MainState(listEquipos = equiposUseCase.getAllEquipo())
            } catch (e: Exception) {
                _uiState.value = MainState(error = e.message)
            }
        }
    }
}