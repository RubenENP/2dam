package com.example.moviescomposerubenhita.ui.biblioteca

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescomposerubenhita.domain.usecases.GetMoviesUseCase
import com.example.moviescomposerubenhita.ui.searchedMovies.SearchedMovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BibliotecaViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<BibliotecaState> by lazy {
        MutableStateFlow(BibliotecaState())
    }
    val uiState: StateFlow<BibliotecaState> = _uiState

    fun handleEvent(event: BibliotecaEvent){
        when(event){
            is BibliotecaEvent.GetAll -> getAll()
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            try {
                val list = getMoviesUseCase.getFavouriteOrSeeLaterMovies()
                _uiState.update {
                    _uiState.value.copy(favoriteList = list.filter { it.favourite==1 }, seeLaterList = list.filter { it.seeLater==1 })
                }
            } catch (e: Exception){
                _uiState.update {
                    _uiState.value.copy(error = e.message)
                }
            }
        }
    }
}