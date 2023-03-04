package com.example.roomconrelaciones.domain.usescases

import com.example.roomconrelaciones.data.Repository
import javax.inject.Inject

class UpdateEquipo @Inject constructor(private val repository: Repository) {
    suspend fun invoke(nombre :String, estadio :String, id: Int) = repository.updateEquipo(nombre, estadio, id)
}