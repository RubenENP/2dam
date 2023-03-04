package com.example.mundialrubenhita.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "registros"
, foreignKeys = [
    ForeignKey(entity = PartidoEntity::class,
    parentColumns = ["idPartido"],
    childColumns = ["partidoId"])
])
data class RegistroEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "balance")
    val balance: Double,
    @ColumnInfo(name = "dinero")
    val dinero: Double,
    val partidoId: Int,
)