package com.example.composerubenhita.data.model

import com.example.composerubenhita.domain.model.Persona

fun Persona.toEntity() = PersonaEntity(0, this.nombre, this.apellido)

fun PersonaEntity.toPersona() = Persona(this.nombre, this.apellido)