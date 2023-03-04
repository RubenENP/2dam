package com.example.navigationrubenhita.domain.useCases

import com.example.navigationrubenhita.data.Repository
import com.example.navigationrubenhita.domain.modelo.Equipo
import javax.inject.Inject

class DeleteEquipo @Inject constructor(private val repository: Repository) {
    suspend fun invoke(equipo: Equipo) = repository.deleteEquipo(equipo)
}