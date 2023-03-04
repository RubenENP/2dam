package com.example.moviescomposerubenhita.ui.main

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescomposerubenhita.data.NetworkResult
import com.example.moviescomposerubenhita.data.toMovie
import com.example.moviescomposerubenhita.data.toSerie
import com.example.moviescomposerubenhita.domain.usecases.GetMoviesUseCase
import com.example.moviescomposerubenhita.domain.usecases.GetSeriesUseCase
import com.example.moviescomposerubenhita.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getSeriesUseCase: GetSeriesUseCase,
) : ViewModel() {
    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    private val _uiState: MutableStateFlow<MainState> by lazy {
        MutableStateFlow(MainState())
    }
    val uiState: StateFlow<MainState> = _uiState

    init {
        getSeriesList()
        getMovieList()
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetMovieList -> getMovieList()
            is MainEvent.LimpiarError -> limpiaError()
            is MainEvent.GetSerieList -> getSeriesList()
        }
    }

    private fun getSeriesList(){
        viewModelScope.launch {
            if (Utils.hasInternetConnection(appContext)) {
                getSeriesUseCase.getTrendingSeries()
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    seriesList = result.data?.results?.map { serie -> serie.toSerie() },
                                    loading = false
                                )
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(loading = true) }
                            is NetworkResult.Error -> _uiState.update {
                                it.copy(
                                    loading = false,
                                    error = result.message
                                )
                            }
                        }
                    }
            } else {
                try {
                    _uiState.update {
                        it.copy(seriesList = getSeriesUseCase.getSeriesFromCache())
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = e.message
                        )
                    }
                }
            }
        }
    }

    private fun getMovieList() {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(appContext)) {
                getMoviesUseCase.getTrendingMovieList()
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    movieList = result.data?.results?.map { movie -> movie.toMovie() },
                                    loading = false
                                )
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(loading = true) }
                            is NetworkResult.Error -> _uiState.update {
                                it.copy(
                                    loading = false,
                                    error = result.message
                                )
                            }
                        }
                    }
            } else {
                try {
                    _uiState.update {
                        it.copy(movieList = getMoviesUseCase.getMovieListFromCache())
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(
                            loading = false,
                            error = e.message
                        )
                    }
                }
            }
        }
    }

    private fun limpiaError() {
        _uiState.update {
            _uiState.value.copy(
                error = null
            )
        }
    }
}