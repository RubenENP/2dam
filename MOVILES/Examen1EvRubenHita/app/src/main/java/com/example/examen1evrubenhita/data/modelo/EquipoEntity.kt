package com.example.examen1evrubenhita.data.modelo

import androidx.room.*


@Entity(tableName = "equipos")
data class EquipoEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "nacionalidad")
    val nacionalidad: String,
    @ColumnInfo(name = "puesto")
    val puesto: Int,
)