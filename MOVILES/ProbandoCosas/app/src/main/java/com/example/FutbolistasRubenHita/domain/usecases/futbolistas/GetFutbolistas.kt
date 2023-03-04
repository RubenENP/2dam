package com.example.ProbandoCosas.domain.usecases.futbolistas

import com.example.ProbandoCosas.data.Repository

class GetFutbolistas {
    operator fun invoke () = Repository.getFutbolistas()
}