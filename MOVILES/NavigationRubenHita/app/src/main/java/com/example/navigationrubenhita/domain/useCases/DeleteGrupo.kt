package com.example.navigationrubenhita.domain.useCases

import com.example.navigationrubenhita.data.Repository
import com.example.navigationrubenhita.domain.modelo.Grupo
import javax.inject.Inject

class DeleteGrupo @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(grupo: Grupo): List<Grupo>{
        repository.deleteGrupo(grupo)
        return repository.getAllGrupos()
    }
}
