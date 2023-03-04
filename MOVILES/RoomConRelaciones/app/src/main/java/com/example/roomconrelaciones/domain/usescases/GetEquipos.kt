package com.example.roomconrelaciones.domain.usescases

import com.example.roomconrelaciones.data.Repository
import com.example.roomconrelaciones.domain.modelo.Equipo
import javax.inject.Inject

class GetEquipos @Inject constructor(val repository: Repository){
    suspend fun invoke(): List<Equipo> {
        val equipos: List<Equipo> = repository.getAllEquipos()

        for (item: Equipo in equipos) {
            item.jugadores.addAll(repository.getJugadores(item))
        }

        return equipos
    }
}