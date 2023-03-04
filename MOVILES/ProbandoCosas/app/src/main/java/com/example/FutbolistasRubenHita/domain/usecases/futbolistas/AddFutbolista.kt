package com.example.ProbandoCosas.domain.usecases.futbolistas

import com.example.ProbandoCosas.data.Repository
import com.example.ProbandoCosas.domain.modelo.Futbolista

class AddFutbolista {
    operator fun invoke (futbolista :Futbolista): Boolean = Repository.addFutbolista(futbolista)
}