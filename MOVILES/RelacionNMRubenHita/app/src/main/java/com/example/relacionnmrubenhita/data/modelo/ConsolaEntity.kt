package com.example.relacionnmrubenhita.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "consolas")
data class ConsolaEntity(
    @PrimaryKey(autoGenerate = true)
    val consolaId: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "modelo")
    val modelo: String,
    @ColumnInfo(name="precio")
    val precio: Int,
)