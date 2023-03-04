package com.example.roomconrelaciones.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomconrelaciones.data.Constantes

@Entity(tableName = Constantes.EQUIPOS)
data class EquipoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = Constantes.NOMBRE)
    val nombre: String,
    @ColumnInfo(name = Constantes.ESTADIO)
    val estadio: String,
)