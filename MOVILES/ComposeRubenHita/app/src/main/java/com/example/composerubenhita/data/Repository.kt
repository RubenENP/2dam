package com.example.composerubenhita.data

import com.example.composerubenhita.data.model.toEntity
import com.example.composerubenhita.data.model.toPersona
import com.example.composerubenhita.domain.model.Persona
import javax.inject.Inject

class Repository @Inject constructor(
    private val personasDao: PersonasDao,
) {
    suspend fun insert(persona: Persona) = personasDao.insert(persona.toEntity())

    suspend fun getAll() = personasDao.getAll().map { it.toPersona() }
}