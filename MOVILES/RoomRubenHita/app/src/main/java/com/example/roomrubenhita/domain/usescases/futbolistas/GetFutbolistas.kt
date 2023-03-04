package com.example.roomrubenhita.domain.usescases.futbolistas

import com.example.roomrubenhita.data.Repository

class GetFutbolistas(private val repository: Repository) {
    suspend operator fun invoke () = repository.getFutbolistas()
}