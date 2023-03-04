package com.example.mundialrubenhita.domain.useCases

import com.example.mundialrubenhita.data.Repository
import com.example.mundialrubenhita.domain.modelo.Equipo
import com.example.mundialrubenhita.domain.modelo.Grupo
import com.example.mundialrubenhita.domain.modelo.PartidoEliminatoria
import com.example.mundialrubenhita.domain.modelo.PartidoEliminatoriaTipo
import javax.inject.Inject
import kotlin.random.Random

class SimuladorUseCase @Inject constructor(private val repository: Repository) {
    suspend fun generarGrupos(): List<Grupo>{
        val allEquipos = repository.getAllEquipos()

        val grupos = emptyList<Grupo>().toMutableList()
        var equipoIndex = 0

        for (i in 0 until (allEquipos.size/4)){
            val listaEquipos = emptyList<Equipo>().toMutableList()
            for (j in 0..3){
                listaEquipos.add(allEquipos[equipoIndex])
                equipoIndex++
            }
            grupos.add(Grupo(listaEquipos))
        }

        return grupos.toList()
    }

    fun generarPartidosEliminatoria(equipos: List<Equipo>): List<PartidoEliminatoria>{
        val partidosEliminatoria = emptyList<PartidoEliminatoria>().toMutableList()
        var index = 0

        for (i in 0 until equipos.size/2){
            partidosEliminatoria.add(PartidoEliminatoria(PartidoEliminatoriaTipo.OCTAVOS, equipos[index], equipos[index+1],
                equipos[Random.nextInt(index, index+1)]))
            index +=2
        }

        var size = partidosEliminatoria.size
        for (i in 0 until size  step 2){
            partidosEliminatoria.add(PartidoEliminatoria(PartidoEliminatoriaTipo.CUARTOS, partidosEliminatoria[i].ganador
            ,partidosEliminatoria[i+1].ganador, partidosEliminatoria[Random.nextInt(i, i+1)].ganador))
        }

        size = partidosEliminatoria.size
        for (i in 8 until size step 2){
            partidosEliminatoria.add(PartidoEliminatoria(PartidoEliminatoriaTipo.SEMIS, partidosEliminatoria[i].ganador
                ,partidosEliminatoria[i+1].ganador, partidosEliminatoria[Random.nextInt(i, i+1)].ganador))
        }


        size = partidosEliminatoria.size
        for (i in size-2 until size step 2){
            partidosEliminatoria.add(PartidoEliminatoria(PartidoEliminatoriaTipo.FINAL, partidosEliminatoria[i].ganador
                ,partidosEliminatoria[i+1].ganador, partidosEliminatoria[Random.nextInt(i, i+1)].ganador))
        }

        return partidosEliminatoria
    }
}