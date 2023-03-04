package com.example.navigationrubenhita.data

import com.example.navigationrubenhita.data.modelo.*
import com.example.navigationrubenhita.domain.modelo.Equipo
import com.example.navigationrubenhita.domain.modelo.Grupo
import com.example.navigationrubenhita.domain.modelo.Partido
import javax.inject.Inject

class Repository @Inject constructor(private val mundialDao: MundialDao){
    val partidosList: MutableList<Partido> = mutableListOf()

    fun getPartidos(): List<Partido> {
        return partidosList
    }

    suspend fun allEquipos() = mundialDao.allEquipos().map { it.toEquipo() }

    suspend fun insertListaGrupos(listaGrupos: List<Grupo>) =
        listaGrupos.map { it.toGrupoEntity() }.let { mundialDao.insertListaGrupos(it) }

    suspend fun getUnGrupo(id: Int) = mundialDao.getUnGrupo(id).toGrupo()

    suspend fun deleteGrupo(grupo: Grupo) = mundialDao.deleteGrupo(grupo.toGrupoEntity())

    suspend fun getAllGrupos(): List<Grupo> = mundialDao.getAllGrupos().map { it.toGrupo() }

    suspend fun deleteEquipo(equipo: Equipo) = mundialDao.deleteEquipo(equipo.toEntity())

    suspend fun updateGrupo(grupo: Grupo) = mundialDao.updateGrupo(grupo.toGrupoEntity())
}