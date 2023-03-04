package com.example.roomconrelaciones.ui.addJugador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.roomconrelaciones.databinding.ActivityAddJugadorBinding
import com.example.roomconrelaciones.domain.modelo.Jugador
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddJugadorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddJugadorBinding

    private val viewModel: AddJugadorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddJugadorBinding.inflate(layoutInflater)

        with(binding){
            setContentView(root)


            viewModel.handleEvent(AddJugadorEvent.GetEquipo(intent.getStringExtra("equipo")!!))

            viewModel.uiState.observe(this@AddJugadorActivity){ state ->
                state.error?.let {
                    Toast.makeText(this@AddJugadorActivity, it, Toast.LENGTH_LONG).show()
                }

                state.jugador?.let {
                    Toast.makeText(this@AddJugadorActivity, it.nombre+ " Añadido", Toast.LENGTH_SHORT).show()
                }
            }

            anyadirButton.setOnClickListener{
                val nombre = editTextNombre.text.toString()

                val posicion: String = when(radioGroup.checkedRadioButtonId){
                    porteroButton.id -> "Portero"
                    defensaButton.id -> "Defensa"
                    mediocentroButton.id -> "Mediocentro"
                    delanteroButton.id -> "Delantero"
                    else -> ""
                }

                val titulos = editTextTitulos.text.toString().toInt()

                val jugador = Jugador(nombre, posicion, titulos)
                val equipo = viewModel.uiState.value?.equipo

                viewModel.handleEvent(AddJugadorEvent.Addjugador(jugador, equipo))
            }
        }
    }
}