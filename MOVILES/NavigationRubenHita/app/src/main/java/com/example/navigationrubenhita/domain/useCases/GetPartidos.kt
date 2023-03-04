package com.example.navigationrubenhita.domain.useCases

import com.example.navigationrubenhita.data.Repository
import javax.inject.Inject

class GetPartidos @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.getPartidos()
}