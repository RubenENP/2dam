package com.example.relacionnmrubenhita.ui.addJugador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.relacionnmrubenhita.R
import com.example.relacionnmrubenhita.databinding.ActivityAddJugadorBinding
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import com.example.relacionnmrubenhita.ui.Constantes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddJugadorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddJugadorBinding

    private val viewModel: AddJugadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddJugadorBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            setButtonListener(binding)

            viewModel.uiState.observe(this@AddJugadorActivity) { state ->
                if (state.jugador != null) {
                    Toast.makeText(
                        this@AddJugadorActivity,
                        state.jugador.nombre + Constantes.ANYADIDO,
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (state.error != null) {
                    Toast.makeText(this@AddJugadorActivity, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setButtonListener(binding: ActivityAddJugadorBinding){
        binding.apply {
            if (intent.extras?.isEmpty == true || intent.extras == null){
                addJugador.setOnClickListener {
                    val jugador = Jugador(
                        0,
                        editTextNombre.text.toString(),
                        editTextDinero.text.toString().toInt(),
                        emptyList()
                    )
                    viewModel.handleEvent(AddJugadorEvent.AddJugador(jugador))
                }
            } else{
                addJugador.setOnClickListener {
                    val jugador = Jugador(
                        0,
                        editTextNombre.text.toString(),
                        editTextDinero.text.toString().toInt(),
                        emptyList()
                    )

                    viewModel.handleEvent(AddJugadorEvent.AddJugadorDentroDeConsola(jugador,
                        intent.getIntExtra(Constantes.CONSOLA_ID,0)))
                }
            }
        }
    }
}