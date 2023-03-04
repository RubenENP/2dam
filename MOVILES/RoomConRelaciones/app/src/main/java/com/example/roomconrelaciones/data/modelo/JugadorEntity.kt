package com.example.roomconrelaciones.data.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.roomconrelaciones.data.Constantes

    @Entity(tableName = Constantes.JUGADORES,
    foreignKeys = [
        ForeignKey(entity = EquipoEntity::class,
        parentColumns = [Constantes.ID],
        childColumns = [Constantes.EQUIPO_ID])
    ])
data class JugadorEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    var equipoid: Int,
    @ColumnInfo(name = Constantes.NOMBRE)
    val nombre: String,
    @ColumnInfo(name = Constantes.POSICION)
    val posicion: String,
    @ColumnInfo(name = Constantes.TITULOS)
    val titulos: Int,
)