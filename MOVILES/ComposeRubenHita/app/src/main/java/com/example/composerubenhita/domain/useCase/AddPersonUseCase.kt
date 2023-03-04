package com.example.composerubenhita.domain.useCase


import com.example.composerubenhita.data.Repository
import com.example.composerubenhita.domain.model.Persona
import javax.inject.Inject

class AddPersonUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend fun invoke(persona: Persona) = repository.insert(persona)
}