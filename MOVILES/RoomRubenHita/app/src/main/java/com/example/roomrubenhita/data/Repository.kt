package com.example.roomrubenhita.data

import com.example.roomrubenhita.data.modelo.FutbolistaEntity
import com.example.roomrubenhita.data.modelo.toFutbolista
import com.example.roomrubenhita.domain.modelo.Futbolista

class Repository(private val futbolistadao: FutbolistaDao) {
    suspend fun addFutbolista(futbolista: FutbolistaEntity) = futbolistadao.insert(futbolista)

    suspend fun getFutbolistas(): List<Futbolista> = futbolistadao.getAll().map { it.toFutbolista() }

    suspend fun deleteFutbolista(futbolista: FutbolistaEntity) = futbolistadao.delete(futbolista.nombre)
}