package com.example.examen1evrubenhita.domain.usecases

import com.example.examen1evrubenhita.data.Repository
import com.example.examen1evrubenhita.domain.modelo.Equipo
import javax.inject.Inject

class DeleteEquipo @Inject constructor(private val repository: Repository) {
    suspend fun invoke(equipo: Equipo) = repository.deleteEquipo(equipo)
}