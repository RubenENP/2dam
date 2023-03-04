package com.example.mundialrubenhita.ui.registros

import com.example.mundialrubenhita.domain.modelo.Registro

data class RegistrosState(val listaRegistros: List<Registro>? = null, val error: String? = null)