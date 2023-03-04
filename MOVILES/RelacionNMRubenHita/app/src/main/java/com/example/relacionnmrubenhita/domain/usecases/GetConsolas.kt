package com.example.relacionnmrubenhita.domain.usecases

import com.example.relacionnmrubenhita.data.Repository
import javax.inject.Inject

class GetConsolas @Inject constructor(private val repository: Repository){
    suspend operator fun invoke() = repository.getConsolas()

    suspend operator fun invoke(id: Int) = repository.getUnaConsola(id)
}