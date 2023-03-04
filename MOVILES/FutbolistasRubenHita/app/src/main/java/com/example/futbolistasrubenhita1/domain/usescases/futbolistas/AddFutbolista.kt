package com.example.futbolistasrubenhita1.domain.usescases.futbolistas

import com.example.futbolistasrubenhita1.data.Repository
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista

class AddFutbolista {
    operator fun invoke (futbolista : Futbolista): Boolean = Repository.addFutbolista(futbolista)
}