package com.example.examen1evrubenhita.data

import com.example.examen1evrubenhita.data.modelo.toEntity
import com.example.examen1evrubenhita.data.modelo.toEquipo
import com.example.examen1evrubenhita.domain.modelo.Equipo
import javax.inject.Inject

class Repository @Inject constructor(private val equipoDao: EquipoDao){
    suspend fun getEquipos() = equipoDao.getEquipos().map { it.toEquipo() }
    suspend fun deleteEquipo(equipo: Equipo) = equipoDao.deleteEquipo(equipo.toEntity())
    suspend fun insertEquipo(equipo: Equipo) = equipoDao.insertEquipo(equipo.toEntity())
    suspend fun getUnEquipo(id: Int) = equipoDao.getUnEquipo(id).toEquipo()
}