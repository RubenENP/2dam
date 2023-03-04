package com.example.moviesrubenhita.ui.genre

import android.content.Context
import android.util.Log
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
class GenerosViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getGenreUseCase: GetGenreUseCase,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<GenerosState> by lazy {
        MutableStateFlow(GenerosState())
    }
    val uiState: StateFlow<GenerosState> = _uiState

    private val _uiError = Channel<String>()
    val uiError = _uiError.receiveAsFlow()

    fun handleEvent(event: GenerosEvent){
        when(event){
            GenerosEvent.GetGenres -> getGenres()
            GenerosEvent.GetMovies -> getMovies()
            is GenerosEvent.Filter -> filter(event.genre)
        }
    }

    private fun filter(genreName: String) {
        viewModelScope.launch {
            try {
                val genreId: Int = _uiState.value.genreList?.first { genre ->
                    genre.name == genreName
                }?.id ?: 28

                _uiState.update {
                    it.copy(
                        movieList = _uiState.value.movieList?.filter { movie -> movie.genreIds.contains(genreId) }
                    )
                }
            }catch (e:Exception){
                _uiState.update {
                    it.copy(
                        error = e.message
                    )
                }
            }
        }
    }

    private fun getGenres(){
        viewModelScope.launch {
            if (Utils.hasInternetConnection(context = appContext)) {
                getGenreUseCase.fetchAllGenres()
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
                                    genreList = result.data?.genres?.map { genre -> genre.toGenre() },
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

                getGenresFromCache()
            }
        }
    }

    private fun getGenresFromCache() {
        viewModelScope.launch {
            try {
                _uiState.update {
                    it.copy(
                        genreList = getGenreUseCase.getGenresFromCache()
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

    private fun getMovies() {
        viewModelScope.launch {

            if (Utils.hasInternetConnection(context = appContext)) {
                getMoviesUseCase.fetchMovieList(3)
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
                                    movieList = result.data?.items?.map { it.toMovie() },
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
                        movieList = getMoviesUseCase.getMoviesFromCache()
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