package com.example.examen1evrubenhita.ui.addEquipo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.examen1evrubenhita.R
import com.example.examen1evrubenhita.databinding.ActivityAddEquipoBinding
import com.example.examen1evrubenhita.databinding.ActivityMainBinding
import com.example.examen1evrubenhita.domain.modelo.Equipo
import com.example.examen1evrubenhita.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEquipoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEquipoBinding

    private val viewModel: AddEquipoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEquipoBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.uiState.observe(this@AddEquipoActivity) { state ->
                state.equipo?.let {
                    Toast.makeText(
                        this@AddEquipoActivity,
                        it.nombre + " Añadido",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                state.error?.let {
                    Toast.makeText(this@AddEquipoActivity, it, Toast.LENGTH_LONG).show()
                }
            }

            addEquipo.setOnClickListener {
                val equipo = Equipo(
                    0,
                    editTextNombre.text.toString(),
                    editTextNacionalidad.text.toString(),
                    editTextPuesto.text.toString().toInt(),
                    null
                )

                viewModel.handleEvent(AddEquipoEvent.AddEquipo(equipo))
            }
        }

    }
}