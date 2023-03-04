package com.example.relacionnmrubenhita.domain.usecases

import com.example.relacionnmrubenhita.data.Repository
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import javax.inject.Inject

class DeleteJugador @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(jugador: Jugador) = repository.deleteJugador(jugador)
}