package com.example.roomconrelaciones.ui.main

import com.example.roomconrelaciones.domain.modelo.Equipo

sealed class MainEvent {
    object GetAllEquipos: MainEvent()
    class DeleteEquipo(val equipo: Equipo): MainEvent()
}