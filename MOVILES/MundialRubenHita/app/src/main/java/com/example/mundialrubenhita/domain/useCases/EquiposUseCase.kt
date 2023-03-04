package com.example.mundialrubenhita.domain.useCases

import com.example.mundialrubenhita.data.Repository
import javax.inject.Inject

class EquiposUseCase @Inject constructor(private val repository: Repository){
    suspend fun getAllEquipo() = repository.getAllEquipos()
}