package com.example.relacionnmrubenhita.domain.usecases

import com.example.relacionnmrubenhita.data.Repository
import com.example.relacionnmrubenhita.data.modelo.toJugador
import javax.inject.Inject

class GetJugadores @Inject constructor(private val repository: Repository){
    suspend operator fun invoke() = repository.getJugadores()

    suspend operator fun invoke(id: Int) = repository.getUnJugador(id)
}