package com.example.examen1evrubenhita.data.modelo

import androidx.room.*

@Entity(tableName = "componentes",
    foreignKeys = [
        ForeignKey(entity = EquipoEntity::class,
            parentColumns = ["id"],
            childColumns = ["idEquipo"
            ])
    ])
data class ComponenteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "tipo")
    val tipo: String,
    @ColumnInfo(name = "nombre")
    val nombre: String,

    val idEquipo: Int,
)