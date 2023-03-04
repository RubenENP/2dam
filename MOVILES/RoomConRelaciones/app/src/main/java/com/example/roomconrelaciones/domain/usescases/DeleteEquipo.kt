package com.example.roomconrelaciones.domain.usescases

import com.example.roomconrelaciones.data.Repository
import com.example.roomconrelaciones.domain.modelo.Equipo
import javax.inject.Inject

class DeleteEquipo @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(equipo: Equipo){
        repository.deleteJugadoresDeUnEquipo(equipo.id)
        repository.deleteEquipo(equipo)
    }
}
