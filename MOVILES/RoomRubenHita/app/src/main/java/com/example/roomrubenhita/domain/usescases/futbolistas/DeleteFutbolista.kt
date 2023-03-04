package com.example.roomrubenhita.domain.usescases.futbolistas

import com.example.roomrubenhita.data.Repository
import com.example.roomrubenhita.data.modelo.toFutbolistaEntity
import com.example.roomrubenhita.domain.modelo.Futbolista

class DeleteFutbolista (private val repository: Repository){
    suspend operator fun invoke(futbolista: Futbolista) = repository.deleteFutbolista(futbolista.toFutbolistaEntity())
}