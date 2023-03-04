package com.example.composerubenhita.domain.useCase

import com.example.composerubenhita.data.Repository
import javax.inject.Inject

class GetPersonsUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun invoke() = repository.getAll()
}