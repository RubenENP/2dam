package com.example.moviescomposerubenhita.ui.searchedMovies

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescomposerubenhita.data.NetworkResult
import com.example.moviescomposerubenhita.data.toMovie
import com.example.moviescomposerubenhita.data.toSerie
import com.example.moviescomposerubenhita.domain.model.Movie
import com.example.moviescomposerubenhita.domain.usecases.GetMoviesUseCase
import com.example.moviescomposerubenhita.domain.usecases.GetSeriesUseCase
import com.example.moviescomposerubenhita.domain.usecases.InsertMovieUseCase
import com.example.moviescomposerubenhita.ui.main.MainState
import com.example.moviescomposerubenhita.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMovieListViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchedMovieListState> by lazy {
        MutableStateFlow(SearchedMovieListState())
    }
    val uiState: StateFlow<SearchedMovieListState> = _uiState

    fun handleEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.LimpiarError -> limpiaError()
            is SearchEvent.SearchMovie -> getMovies(event.name)
            is SearchEvent.InsertFavorite -> insertFavourite(event.movie)
            is SearchEvent.InsertSeeLater -> insertSeeLater(event.movie)
        }
    }

    private fun insertSeeLater(movie: Movie) {
        viewModelScope.launch {
            try {
                insertMovieUseCase.insertSeeLater(movie)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message)
                }
            }
        }
    }

    private fun insertFavourite(movie: Movie) {
        viewModelScope.launch {
            try {
                insertMovieUseCase.insertFavorite(movie)
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(error = e.message)
                }
            }
        }
    }

    private fun getMovies(name: String) {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(appContext)) {
                getMoviesUseCase.searchMovieList(name)
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    listaPeliculas = result.data?.results?.map { movieQuery -> movieQuery.toMovie() },
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
                _uiState.update {
                    _uiState.value.copy(error = "No estas conectado a internet")
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