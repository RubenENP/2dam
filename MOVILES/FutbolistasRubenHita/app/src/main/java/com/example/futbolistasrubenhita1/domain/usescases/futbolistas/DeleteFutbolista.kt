package com.example.futbolistasrubenhita1.domain.usescases.futbolistas

import com.example.futbolistasrubenhita1.data.Repository

class DeleteFutbolista {
    operator fun invoke(id: Int) = Repository.deleteFutbolista(id)
}