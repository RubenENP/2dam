package com.example.roomconrelaciones.domain.usescases

import com.example.roomconrelaciones.data.Repository
import com.example.roomconrelaciones.data.modelo.toEntity
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador
import javax.inject.Inject

class DeleteJugador @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(jugador: Jugador, equipo: Equipo) = repository.deleteJugador(jugador, equipo)
}