package com.example.mundialrubenhita.domain.modelo

data class Apuesta(val partido: Partido, val apuesta: Int, val cantidadApostada: Int) {
    override fun toString(): String {
        val equipoApostadoNombre = when(apuesta){
            1 -> partido.equipoLocal.nombre
            2 -> "Empate"
            3 -> partido.equipoVisitante.nombre
            else -> {"ERROR"}
        }

        return when(partido.resultado){
            1 -> "Tu apuesta: $equipoApostadoNombre " +
                    "\nHa ganado el ${partido.equipoLocal.nombre} ${partido.golesLocal}-${partido.golesVisitante}"
            2 -> "Tu apuesta: $equipoApostadoNombre " +
                    "\nEmpate ${partido.golesLocal}-${partido.golesVisitante}"
            3 -> "Tu apuesta: $equipoApostadoNombre " +
                    "\nHa ganado el ${partido.equipoVisitante.nombre} ${partido.golesLocal}-${partido.golesVisitante}"
            else -> {"ERROR"}
        }
    }
}