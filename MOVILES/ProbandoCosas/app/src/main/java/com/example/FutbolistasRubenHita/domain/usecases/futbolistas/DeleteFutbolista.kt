package com.example.ProbandoCosas.domain.usecases.futbolistas

import com.example.ProbandoCosas.data.Repository

class DeleteFutbolista {
    operator fun invoke(id: Int) = Repository.deleteFutbolista(id)
}