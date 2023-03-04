package com.example.roomrubenhita.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "futbolistas")
data class FutbolistaEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "posicion")
    val posicion: String,
    @ColumnInfo(name = "balonesDeOro")
    val balonesDeOro: Int,
    @ColumnInfo(name = "championsGanadas")
    val championsGanadas: Int
)