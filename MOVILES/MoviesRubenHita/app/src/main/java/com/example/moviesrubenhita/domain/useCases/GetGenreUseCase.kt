package com.example.moviesrubenhita.domain.useCases

import com.example.moviesrubenhita.data.GenresRepository
import com.example.moviesrubenhita.data.NetworkResult
import com.example.moviesrubenhita.data.modelo.genre.Genres
import com.example.moviesrubenhita.domain.modelo.Genre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(private val repository: GenresRepository) {
    fun fetchAllGenres(): Flow<NetworkResult<Genres>> = repository.fetchAllGenres()
    suspend fun getGenresFromCache(): List<Genre> = repository.getGenresFromCache()
}