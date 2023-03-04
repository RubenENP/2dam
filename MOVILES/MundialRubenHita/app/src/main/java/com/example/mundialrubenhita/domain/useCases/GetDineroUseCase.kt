package com.example.mundialrubenhita.domain.useCases

import com.example.mundialrubenhita.data.Repository
import com.example.mundialrubenhita.domain.modelo.Registro
import javax.inject.Inject

class GetDineroUseCase @Inject constructor(private val repository: Repository) {
    suspend fun getDinero(): Double{
        val listaRegistros :List<Registro> = repository.getAllRegistros()

        val dinero: Double = if (listaRegistros.isEmpty()){
            1000.0
        } else{
            listaRegistros.last().dinero
        }

        return dinero
    }
}