package com.example.moviesrubenhita.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrubenhita.data.NetworkResult
import com.example.moviesrubenhita.data.toMovie
import com.example.flowsrubenhita.ui.MainEvent
import com.example.flowsrubenhita.utils.Utils
import com.example.moviesrubenhita.domain.useCases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainState> by lazy {
        MutableStateFlow(MainState())
    }
    val uiState: StateFlow<MainState> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetUnaPeli -> getUnaPeli(event.id)
        }
    }

    private fun getUnaPeli(id: Int) {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = appContext)) {
                getMoviesUseCase.fetchMovieList(id)
                    .catch(action = { cause -> _uiError.send(cause.message ?: "") })
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        isLoading = false
                                    )
                                }
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    movies = result.data?.items?.map { movie -> movie.toMovie() },
                                    isLoading = false
                                )
                            }
                        }
                    }
            } else {
                _uiState.update {
                    it.copy(
                        error = "no hay internet cargando de cache.",
                        isLoading = false
                    )
                }

                getMoviesFromCache()
            }
        }
    }

    private fun getMoviesFromCache() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        movies = getMoviesUseCase.getMoviesFromCache()
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message
                    )
                }
            }
        }
    }
}