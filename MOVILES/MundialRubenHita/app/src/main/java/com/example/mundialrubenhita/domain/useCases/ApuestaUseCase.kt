package com.example.mundialrubenhita.domain.useCases

import com.example.mundialrubenhita.data.Repository
import com.example.mundialrubenhita.domain.modelo.Partido
import com.example.mundialrubenhita.domain.modelo.Registro
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import kotlin.random.Random

class ApuestaUseCase @Inject constructor(private val repository: Repository) {
    suspend fun apostar(
        cantidad: Int,
        apuesta: Int,
        dineroTotal: Double,
        partido: Partido,
        precioLocal: Double,
        precioVisitante: Double,
        precioEmpate: Double
    ) : Partido{
        val equipoLocal = partido.equipoLocal
        val equipoVisitante = partido.equipoVisitante
        var golesLocal = 0
        var golesVisitante = 0

        var minutos = 0

        while (minutos <= 90) {
            val a = Random.nextInt(0, equipoLocal.rankingFifa)
            if (a < equipoVisitante.rankingFifa) {
                val oportunidadDeGol = Random.nextInt(0, 20)
                if (oportunidadDeGol == 0) {
                    golesLocal++
                }
            }

            val b = Random.nextInt(0, equipoVisitante.rankingFifa)
            if (b < equipoLocal.rankingFifa) {
                val oportunidadDeGol = Random.nextInt(0, 20)
                if (oportunidadDeGol == 0) {
                    golesVisitante++
                }
            }

            minutos++
        }

        partido.golesLocal = golesLocal
        partido.golesVisitante = golesVisitante

        if (golesLocal > golesVisitante) {
            partido.resultado = 1
        } else if (golesVisitante > golesLocal) {
            partido.resultado = 3
        } else {
            partido.resultado = 2
        }

        repository.updatePartido(partido)

        var balance = 0.0
        var dinero = 0.0

        if (apuesta == partido.resultado) {
            when (apuesta) {
                1 -> {
                    val dineroGanado = cantidad * precioLocal
                    balance += BigDecimal(dineroGanado).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                    dinero = dineroTotal + dineroGanado
                }
                2 -> {
                    val dineroGanado = cantidad * precioEmpate
                    balance += BigDecimal(dineroGanado).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                    dinero = dineroTotal + dineroGanado
                }
                3 -> {
                    val dineroGanado = cantidad * precioVisitante
                    balance += BigDecimal(dineroGanado).setScale(2, RoundingMode.HALF_EVEN).toDouble()
                    dinero = dineroTotal + dineroGanado
                }
            }
        } else {
            balance -= cantidad
            dinero = dineroTotal+balance
        }

        repository.insertRegistro(Registro(0, balance, BigDecimal(dinero).setScale(2, RoundingMode.HALF_EVEN).toDouble(),
            partido.idPartido, null))

        return partido
    }
}