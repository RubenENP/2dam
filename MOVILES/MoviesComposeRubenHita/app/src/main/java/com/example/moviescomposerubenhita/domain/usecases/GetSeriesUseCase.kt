package com.example.moviescomposerubenhita.domain.usecases

import com.example.moviescomposerubenhita.data.NetworkResult
import com.example.moviescomposerubenhita.data.Repository
import com.example.moviescomposerubenhita.data.modelo.series.Series
import com.example.moviescomposerubenhita.domain.model.Serie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.random.Random

class GetSeriesUseCase @Inject constructor(
    private val repository: Repository,
){
    fun getTrendingSeries(): Flow<NetworkResult<Series>> {
        return repository.fetchTrendingSerieList(Random.nextInt(1,10))
    }

    suspend fun getSeriesFromCache(): List<Serie> {
        return repository.getSeriesFromCache()
    }
}