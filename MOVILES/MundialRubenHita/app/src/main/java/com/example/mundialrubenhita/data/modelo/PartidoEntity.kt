package com.example.mundialrubenhita.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "partidos")
data class PartidoEntity(
    @PrimaryKey(autoGenerate = true)
    val idPartido: Int,
    @ColumnInfo(name = "golesLocal")
    val golesLocal: Int?,
    @ColumnInfo(name = "golesVisitante")
    val golesVisitante: Int?,
    @ColumnInfo(name = "resultado")
    val resultado: Int?,
)