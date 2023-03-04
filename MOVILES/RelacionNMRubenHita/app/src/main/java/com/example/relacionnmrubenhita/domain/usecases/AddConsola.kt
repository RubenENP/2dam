package com.example.relacionnmrubenhita.domain.usecases

import com.example.relacionnmrubenhita.data.Repository
import com.example.relacionnmrubenhita.domain.modelo.Consola
import javax.inject.Inject

class AddConsola @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(consola: Consola) = repository.insertConsola(consola)
}