package com.example.moviesrubenhita.ui.buscador

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowsrubenhita.utils.Utils
import com.example.moviesrubenhita.data.NetworkResult
import com.example.moviesrubenhita.data.toGenre
import com.example.moviesrubenhita.data.toMovie
import com.example.moviesrubenhita.domain.useCases.GetGenreUseCase
import com.example.moviesrubenhita.domain.useCases.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuscadorViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getGenreUseCase: GetGenreUseCase,
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<BuscadorState> by lazy {
        MutableStateFlow(BuscadorState())
    }
    val uiState: StateFlow<BuscadorState> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: BuscadorEvent) {
        when (event) {
            is BuscadorEvent.GetMoviesByName -> getMovies(event.name)
        }
    }

    private fun getMovies(name: String) {
        viewModelScope.launch {

            if (Utils.hasInternetConnection(context = appContext)) {
                getMoviesUseCase.fetchMovieListByName(name)
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
                            is NetworkResult.Success -> _uiState.update { state ->
                                state.copy(
                                    moviesList = result.data?.results?.map { it.toMovie() },
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
                        moviesList = getMoviesUseCase.getMoviesFromCache()
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