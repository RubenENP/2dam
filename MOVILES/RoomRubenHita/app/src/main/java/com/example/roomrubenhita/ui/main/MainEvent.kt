package com.example.roomrubenhita.ui.main

import com.example.roomrubenhita.domain.modelo.Futbolista

sealed class MainEvent {
    class DeleteFutbolista(val futbolista: Futbolista): MainEvent()
    object GetAllFutbolistas: MainEvent()
}