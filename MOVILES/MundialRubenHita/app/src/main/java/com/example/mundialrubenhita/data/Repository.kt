package com.example.mundialrubenhita.data

import com.example.mundialrubenhita.data.modelo.*
import com.example.mundialrubenhita.domain.modelo.Partido
import com.example.mundialrubenhita.domain.modelo.Registro
import javax.inject.Inject

class Repository @Inject constructor(private val mundialDao: MundialDao) {
    suspend fun getAllEquipos() = mundialDao.getAllEquipos().map { it.toEquipo() }

    suspend fun insertPartidos(partidosEntity: List<PartidoEntity>) =
        mundialDao.insertListPartidos(partidosEntity)

    suspend fun getAllPartidos(): List<Partido> = mundialDao.getAllPartidos().map { it.toPartido() }

    suspend fun insertCrossRef(idPartido: Int, idEquipo: Int) =
        mundialDao.insertCrossRef(PartidoEquipoCrossRef(idPartido, idEquipo))

    suspend fun getPartidosId(): List<Int> = mundialDao.getPartidosId()

    suspend fun getUnPartido(id: Int) = mundialDao.getUnPartido(id).toPartido()

    suspend fun getAllRegistros() = mundialDao.getAllRegistros().map { it.toRegistro() }

    suspend fun insertRegistro(registro: Registro) = mundialDao.insertRegistro(registro.toRegistroEntity())

    suspend fun updatePartido(partido: Partido) = mundialDao.updatePartido(partido.toPartidoEntity())

    suspend fun getAllPartidosSinJugar() = mundialDao.getPartidosSinJugar().map { it.toPartido() }
}