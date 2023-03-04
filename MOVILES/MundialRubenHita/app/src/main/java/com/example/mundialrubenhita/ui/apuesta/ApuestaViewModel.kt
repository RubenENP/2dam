package com.example.mundialrubenhita.ui.apuesta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mundialrubenhita.domain.modelo.Apuesta
import com.example.mundialrubenhita.domain.useCases.ApuestaUseCase
import com.example.mundialrubenhita.domain.useCases.GetDineroUseCase
import com.example.mundialrubenhita.domain.useCases.PartidosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject
import kotlin.math.absoluteValue
import kotlin.random.Random

@HiltViewModel
class ApuestaViewModel @Inject constructor(
    private val partidosUseCase: PartidosUseCase,
    private val getDineroUseCase: GetDineroUseCase,
    private val apuestaUseCase: ApuestaUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData(ApuestaState())
    val uiState: LiveData<ApuestaState> get() = _uiState

    fun handleEvent(event: ApuestaEvent) {
        when (event) {
            is ApuestaEvent.GetPartido -> getPartido(event.idPartido)
            is ApuestaEvent.Apostar -> apostar(
                event.cantidad,
                event.apuesta,
            )
        }
    }

    init {
        getDinero()
    }

    private fun getDinero() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value?.copy(dinero = getDineroUseCase.getDinero())
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }

    private fun getPartido(idPartido: Int) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value?.copy(partido = partidosUseCase.getUnPartido(idPartido))

                setPrecios()
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }

    private fun setPrecios() {
        var precioLocal = 0.0
        var precioVisitante = 0.0
        val precioEmpate = 2.0

        val diferencia = _uiState.value?.partido?.equipoLocal?.rankingFifa?.minus(
            _uiState.value?.partido?.equipoVisitante?.rankingFifa!!
        )

        if (diferencia != null) {
            val diferenciaAbsoluta = diferencia.absoluteValue
            if (diferencia > 0) {
                precioVisitante =
                    diferenciaAbsoluta.toDouble() - (diferenciaAbsoluta - Random.nextDouble(
                        1.1,
                        1.5
                    ))
                precioLocal =
                    (diferenciaAbsoluta / 2).toDouble() - ((diferenciaAbsoluta / 3) - Random.nextDouble(
                        2.5,
                        3.0
                    ))

            } else {
                precioLocal =
                    diferenciaAbsoluta.toDouble() - (diferenciaAbsoluta - Random.nextDouble(
                        1.1,
                        1.5
                    ))
                precioVisitante =
                    (diferenciaAbsoluta / 2).toDouble() - ((diferenciaAbsoluta / 3) - Random.nextDouble(
                        2.5,
                        3.0
                    ))
            }
        }

        _uiState.value = _uiState.value?.copy(
            precioLocal = BigDecimal(precioLocal).setScale(2, RoundingMode.HALF_EVEN).toDouble(),
            precioVisitante = BigDecimal(precioVisitante).setScale(2, RoundingMode.HALF_EVEN).toDouble(),
            precioEmpate = precioEmpate
        )
    }

    private fun apostar(cantidad: Int, apuesta: Int) {
        viewModelScope.launch {
            try {
                _uiState.value?.partido?.let { partido ->
                    _uiState.value!!.precioLocal?.let { precioLocal ->
                        _uiState.value!!.precioVisitante?.let { precioVisitante ->
                            _uiState.value!!.precioEmpate?.let { precioEmpate ->
                                _uiState.value = _uiState.value!!.copy(
                                    partido = apuestaUseCase.apostar(
                                        cantidad,
                                        apuesta,
                                        getDineroUseCase.getDinero(),
                                        partido,
                                        precioLocal,
                                        precioVisitante,
                                        precioEmpate,
                                    )
                                )
                                val partidoJugado = _uiState.value!!.partido
                                _uiState.value = _uiState.value!!.copy(apuestaModel = partidoJugado?.let {
                                    Apuesta(
                                        it, apuesta, cantidad)
                                })
                            }
                        }
                    }

                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value?.copy(error = e.message)
            }
        }
    }
}