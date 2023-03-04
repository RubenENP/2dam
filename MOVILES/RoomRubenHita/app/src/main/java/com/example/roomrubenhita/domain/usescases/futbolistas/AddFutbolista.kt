package com.example.roomrubenhita.domain.usescases.futbolistas

import com.example.roomrubenhita.data.FutbolistaDao
import com.example.roomrubenhita.data.Repository
import com.example.roomrubenhita.data.modelo.toFutbolistaEntity
import com.example.roomrubenhita.domain.modelo.Futbolista

class AddFutbolista (private val repository: Repository){
    suspend operator fun invoke (futbolista : Futbolista) = repository.addFutbolista(futbolista.toFutbolistaEntity())
}