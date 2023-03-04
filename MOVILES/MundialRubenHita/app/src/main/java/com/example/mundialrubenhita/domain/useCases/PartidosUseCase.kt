package com.example.mundialrubenhita.domain.useCases

import com.example.mundialrubenhita.data.Repository
import com.example.mundialrubenhita.data.modelo.PartidoEntity
import com.example.mundialrubenhita.domain.modelo.Partido
import javax.inject.Inject
import kotlin.random.Random

class PartidosUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getPartidosSinJugar(): List<Partido> {
        val allPartidos: List<Partido> = repository.getAllPartidosSinJugar()

        if (allPartidos.isEmpty()) {
            generarPartidos()
        }

        return repository.getAllPartidosSinJugar().takeLast(7)
    }

    suspend fun getPartidos(): List<Partido> {
        val allPartidos: List<Partido> = repository.getAllPartidos()

        if (allPartidos.isEmpty()) {
            generarPartidos()
        }

        return repository.getAllPartidos().takeLast(7)
    }

    suspend fun generarPartidos() {
        val partidos: MutableList<PartidoEntity> = mutableListOf()

        for (i in 0..6) {
            partidos.add(PartidoEntity(0, null, null, null))
        }

        repository.insertPartidos(partidos.toList())

        val allPartidos: List<Int> = repository.getPartidosId()

        allPartidos.takeLast(7).forEach { id ->
            val equipoUnoId = Random.nextInt(1, 32)
            repository.insertCrossRef(id, equipoUnoId)
            var equipoDosId = Random.nextInt(1, 32)
            while (equipoDosId == equipoUnoId) {
                equipoDosId = Random.nextInt(1, 32)
            }
            repository.insertCrossRef(id, equipoDosId)
        }
    }

    suspend fun getUnPartido(id: Int) = repository.getUnPartido(id)

}