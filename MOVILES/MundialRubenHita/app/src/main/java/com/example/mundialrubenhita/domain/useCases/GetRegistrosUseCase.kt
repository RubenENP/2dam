package com.example.mundialrubenhita.domain.useCases

import com.example.mundialrubenhita.data.Repository
import com.example.mundialrubenhita.domain.modelo.Registro
import javax.inject.Inject

class GetRegistrosUseCase @Inject constructor(private val repository: Repository) {
    suspend fun invoke() :List<Registro>{
        val registros = repository.getAllRegistros()

        registros.forEach { registro ->
            registro.partido = repository.getUnPartido(registro.idPartido)
        }

        return registros
    }
}