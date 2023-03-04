package com.example.futbolistasrubenhita1.domain.usescases.futbolistas

import com.example.futbolistasrubenhita1.data.Repository

class GetFutbolistas {
    operator fun invoke () = Repository.getFutbolistas()
}