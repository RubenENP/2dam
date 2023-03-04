package com.example.flowsrubenhita.ui

sealed class MainEvent {
    class GetUnaPeli(val id:Int) : MainEvent()
}