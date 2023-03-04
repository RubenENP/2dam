package com.example.relacionnmrubenhita.domain.usecases

import com.example.relacionnmrubenhita.data.Repository
import javax.inject.Inject

class AddCrossRef @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(jugadorId: Int, consolaId: Int) = repository.addCrossRef(jugadorId, consolaId)
}