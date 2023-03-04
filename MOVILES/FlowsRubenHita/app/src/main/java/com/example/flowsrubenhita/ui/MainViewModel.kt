package com.example.flowsrubenhita.ui

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowsrubenhita.data.NetworkResult
import com.example.flowsrubenhita.data.Repository
import com.example.flowsrubenhita.data.toMovie
import com.example.flowsrubenhita.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val repository: Repository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainState> by lazy {
        MutableStateFlow(MainState())
    }
    val uiState: StateFlow<MainState> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    @RequiresApi(Build.VERSION_CODES.M)
    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetUnaPeli -> getUnaPeli(event.id)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun getUnaPeli(id: Int) {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = appContext)) {
                repository.fetchMovieList(id)
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
                                    movies = result.data?.items?.map { movie -> movie.toMovie() }, isLoading = false
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
            }
        }
    }
}