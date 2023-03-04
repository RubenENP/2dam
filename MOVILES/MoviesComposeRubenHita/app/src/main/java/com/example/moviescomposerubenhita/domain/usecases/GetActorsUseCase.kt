package com.example.moviescomposerubenhita.domain.usecases

import com.example.moviescomposerubenhita.data.NetworkResult
import com.example.moviescomposerubenhita.data.Repository
import com.example.moviescomposerubenhita.data.modelo.actors.Actors
import com.example.moviescomposerubenhita.domain.model.Actor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActorsUseCase @Inject constructor(
    private val repository: Repository
) {
    fun fetchActorList(): Flow<NetworkResult<Actors>> = repository.fetchActorsList()
    suspend fun getActorsFromCache(): List<Actor> = repository.getActorsCache()
}