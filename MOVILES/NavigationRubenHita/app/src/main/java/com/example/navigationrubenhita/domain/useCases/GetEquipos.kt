package com.example.navigationrubenhita.domain.useCases

import com.example.navigationrubenhita.data.Repository
import javax.inject.Inject

class GetEquipos @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.allEquipos()
}