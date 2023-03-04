package com.example.roomrubenhita.ui.futbolista

import com.example.roomrubenhita.domain.modelo.Futbolista

sealed class FutbolistaEvent {
    class AddFutbolista(val futbolista: Futbolista): FutbolistaEvent()
    class GetFutbolista(val nombre :String): FutbolistaEvent()
}