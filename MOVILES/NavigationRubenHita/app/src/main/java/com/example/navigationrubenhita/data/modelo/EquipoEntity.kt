package com.example.navigationrubenhita.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipos")
data class EquipoEntity (
    @PrimaryKey(autoGenerate = false)
    val idEquipo: Int,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "escudoImg")
    val escudoImg: String,
)