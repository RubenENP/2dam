package com.example.roomrubenhita.domain.modelo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize;

@Parcelize
data class Futbolista(
    val nombre: String,
    val posicion: String,
    val balonesDeOro: Int,
    val championsGanadas: Int
) : Parcelable