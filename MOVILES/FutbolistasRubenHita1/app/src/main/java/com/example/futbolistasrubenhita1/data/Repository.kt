package com.example.futbolistasrubenhita1.data

import com.example.futbolistasrubenhita1.domain.modelo.Futbolista

object Repository {
    private val futbolistas = mutableListOf<Futbolista>()

    init {
        futbolistas.add(Futbolista("El Bicho", "Delantero", 5, 5))
        futbolistas.add(Futbolista("Messi", "Delantero", 7, 4))
        futbolistas.add(Futbolista("Vinicius", "Delantero", 0, 1))
        futbolistas.add(Futbolista("Hazard", "Burger King", 0, 2))
        futbolistas.add(Futbolista("Kristian Kozdeba", "Portero", 10, 6))

    }

    fun addFutbolista(futbolista: Futbolista): Boolean {
        return futbolistas.add(futbolista)
    }

    fun getFutbolistas(): List<Futbolista> {
        return futbolistas
    }

    fun deleteFutbolista(id: Int) {
        futbolistas.remove(futbolistas[id])
    }
}