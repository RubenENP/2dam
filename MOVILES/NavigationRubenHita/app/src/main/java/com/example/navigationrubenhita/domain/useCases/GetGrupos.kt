package com.example.navigationrubenhita.domain.useCases

import com.example.navigationrubenhita.data.Repository
import com.example.navigationrubenhita.domain.modelo.Equipo
import com.example.navigationrubenhita.domain.modelo.Grupo
import javax.inject.Inject

class GetGrupos @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): List<Grupo> {
        val allEquipos = repository.allEquipos()

        val equiposShuffled = allEquipos.shuffled()

        val listaGrupos: MutableList<Grupo> = mutableListOf()

        listaGrupos.add(
            Grupo(
                1,
                mutableListOf(
                    equiposShuffled[0],
                    equiposShuffled[1],
                    equiposShuffled[2],
                    equiposShuffled[3]
                )
            )
        )
        listaGrupos.add(
            Grupo(
                2,
                mutableListOf(
                    equiposShuffled[4],
                    equiposShuffled[5],
                    equiposShuffled[6],
                    equiposShuffled[7]
                )
            )
        )
        listaGrupos.add(
            Grupo(
                3,
                mutableListOf(
                    equiposShuffled[8],
                    equiposShuffled[9],
                    equiposShuffled[10],
                    equiposShuffled[11]
                )
            )
        )
        listaGrupos.add(
            Grupo(
                4,
                mutableListOf(
                    equiposShuffled[12],
                    equiposShuffled[13],
                    equiposShuffled[14],
                    equiposShuffled[15]
                )
            )
        )
        listaGrupos.add(
            Grupo(
                5,
                mutableListOf(
                    equiposShuffled[16],
                    equiposShuffled[17],
                    equiposShuffled[18],
                    equiposShuffled[19]
                )
            )
        )
        listaGrupos.add(
            Grupo(
                6,
                mutableListOf(
                    equiposShuffled[20],
                    equiposShuffled[21],
                    equiposShuffled[22],
                    equiposShuffled[23]
                )
            )
        )
        listaGrupos.add(
            Grupo(
                7,
                mutableListOf(
                    equiposShuffled[24],
                    equiposShuffled[25],
                    equiposShuffled[26],
                    equiposShuffled[27]
                )
            )
        )
        listaGrupos.add(
            Grupo(
                8,
                mutableListOf(
                    equiposShuffled[28],
                    equiposShuffled[29],
                    equiposShuffled[30],
                    equiposShuffled[31]
                )
            )
        )

        repository.insertListaGrupos(listaGrupos.toList())

        return listaGrupos.toList()
    }

    suspend fun getUnGrupo(id: Int): Grupo {
        return repository.getUnGrupo(id)
    }

    suspend fun getAllGrupos(): Int = repository.getAllGrupos().size
}