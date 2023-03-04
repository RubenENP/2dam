package com.example.examen1evrubenhita.domain.usecases

import com.example.examen1evrubenhita.data.Repository
import com.example.examen1evrubenhita.domain.modelo.Equipo
import javax.inject.Inject

class AddEquipo @Inject constructor(val repository: Repository) {
    suspend fun invoke(equipo: Equipo) = repository.insertEquipo(equipo)
}