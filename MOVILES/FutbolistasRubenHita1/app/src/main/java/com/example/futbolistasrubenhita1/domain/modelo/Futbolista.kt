package com.example.futbolistasrubenhita1.domain.modelo

data class Futbolista(val nombre:String, val posicion:String, val balonesDeOro: Int, val championsGanadas: Int) {
    override fun toString(): String{
        return "Nombre: "+nombre +
                "\nPosicion: "+posicion+
                "\nBalones de oro: "+balonesDeOro+
                "\nChampions Ganadas: "+championsGanadas
    }
}