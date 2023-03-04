package com.example.moviescomposerubenhita.ui.actores

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviescomposerubenhita.data.NetworkResult
import com.example.moviescomposerubenhita.data.toActor
import com.example.moviescomposerubenhita.data.toSerie
import com.example.moviescomposerubenhita.domain.usecases.GetActorsUseCase
import com.example.moviescomposerubenhita.ui.searchedMovies.SearchedMovieListState
import com.example.moviescomposerubenhita.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ActoresViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getActorsUseCase: GetActorsUseCase,
):ViewModel() {
    private val _uiState: MutableStateFlow<ActorsState> by lazy {
        MutableStateFlow(ActorsState())
    }
    val uiState: StateFlow<ActorsState> = _uiState

    fun handleEvent(event: ActorsEvent){
        when(event){
            is ActorsEvent.GetAll -> getAll()
        }
    }

    private fun getAll() {
        viewModelScope.launch {
            if (Utils.hasInternetConnection(appContext)) {
                getActorsUseCase.fetchActorList()
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(
                                    listActors = result.data?.results?.map { a -> a.toActor() },
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
                        it.copy(listActors = getActorsUseCase.getActorsFromCache())
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
}