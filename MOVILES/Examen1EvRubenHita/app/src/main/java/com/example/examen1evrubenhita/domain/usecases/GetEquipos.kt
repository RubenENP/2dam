package com.example.examen1evrubenhita.domain.usecases

import com.example.examen1evrubenhita.data.Repository
import javax.inject.Inject

class GetEquipos @Inject constructor(private val repository: Repository) {
    suspend fun invoke() = repository.getEquipos()

    suspend fun invoke(id: Int) = repository.getUnEquipo(id)
}