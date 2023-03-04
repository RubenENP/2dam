package com.example.navigationrubenhita.domain.useCases

import com.example.navigationrubenhita.data.Repository
import com.example.navigationrubenhita.domain.modelo.Grupo
import javax.inject.Inject

class UpdateGrupo @Inject constructor(private val repository: Repository) {
    suspend fun invoke(grupo: Grupo) = repository.updateGrupo(grupo)
}