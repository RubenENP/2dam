package com.example.roomconrelaciones.domain.usescases

import com.example.roomconrelaciones.data.Repository
import com.example.roomconrelaciones.domain.modelo.Equipo
import javax.inject.Inject

class GetJugadores @Inject constructor(val repository: Repository) {
    suspend fun invoke(equipo: Equipo) = repository.getJugadores(equipo)
}