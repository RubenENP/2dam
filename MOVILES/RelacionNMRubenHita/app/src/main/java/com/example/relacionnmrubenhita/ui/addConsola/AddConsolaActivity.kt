package com.example.relacionnmrubenhita.ui.addConsola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.relacionnmrubenhita.databinding.ActivityAddConsolaBinding
import com.example.relacionnmrubenhita.databinding.ActivityAddJugadorBinding
import com.example.relacionnmrubenhita.databinding.ActivityConsolaBinding
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import com.example.relacionnmrubenhita.ui.Constantes
import com.example.relacionnmrubenhita.ui.addJugador.AddJugadorEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddConsolaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddConsolaBinding

    private val viewModel: AddConsolaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddConsolaBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            setButtonListener(binding)

            viewModel.uiState.observe(this@AddConsolaActivity) { state ->
                if (state.consola != null) {
                    Toast.makeText(
                        this@AddConsolaActivity,
                        state.consola.nombre + Constantes.ANYADIDO,
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (state.error != null) {
                    Toast.makeText(this@AddConsolaActivity, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setButtonListener(binding: ActivityAddConsolaBinding) {
        binding.apply {
            if (intent.extras?.isEmpty == true) {
                addConsola.setOnClickListener {
                    val consola = Consola(
                        0,
                        editTextNombre.text.toString(),
                        editTextMarca.text.toString(),
                        editTextPrecio.text.toString().toInt(),
                        emptyList(),
                    )
                    viewModel.handleEvent(AddConsolaEvent.AddConsola(consola))
                }
            } else {
                addConsola.setOnClickListener {
                    val consola = Consola(
                        0,
                        editTextNombre.text.toString(),
                        editTextMarca.text.toString(),
                        editTextPrecio.text.toString().toInt(),
                        emptyList(),
                    )

                    viewModel.handleEvent(
                        AddConsolaEvent.AddConsolaDentroDeJugador(
                            consola,
                            intent.getIntExtra(Constantes.JUGADOR_ID, 0)
                        )
                    )
                }
            }
        }
    }
}