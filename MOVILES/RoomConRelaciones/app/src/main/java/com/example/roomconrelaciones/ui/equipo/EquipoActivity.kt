package com.example.roomconrelaciones.ui.equipo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomconrelaciones.databinding.ActivityEquipoBinding
import com.example.roomconrelaciones.databinding.ActivityMainBinding
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador
import com.example.roomconrelaciones.ui.addEquipo.AddEquipoActivity
import com.example.roomconrelaciones.ui.addJugador.AddJugadorActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EquipoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEquipoBinding

    private val viewModel: EquipoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEquipoBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(EquipoEvent.GetEquipo(intent.getStringExtra("equipo")))

            val adapter = JugadorAdapter(
                object:JugadorAdapter.Actions {
                    override fun borrarJugador(jugador: Jugador) {
                        viewModel.handleEvent(EquipoEvent.BorrarJugador(jugador))
                        viewModel.handleEvent(EquipoEvent.GetEquipo(viewModel.uiState.value?.equipo?.nombre))
                    }
                }
            )

            recyclerViewJugadores.adapter = adapter
            recyclerViewJugadores.layoutManager = LinearLayoutManager(this@EquipoActivity)

            viewModel.uiState.observe(this@EquipoActivity){ state ->
                state.equipo?.let {
                    editTextEquipo.editText?.setText(it.nombre)
                    editTextEstadio.editText?.setText(it.estadio)

                    adapter.submitList(it.jugadores)
                }

                state.error?.let {
                    Toast.makeText(this@EquipoActivity, it, Toast.LENGTH_LONG).show()
                }
            }

            addJugador.setOnClickListener {
                val intent = Intent(this@EquipoActivity, AddJugadorActivity::class.java)
                intent.putExtra("equipo", viewModel.uiState.value?.equipo?.nombre)
                startActivity(intent)
            }

            updateButton.setOnClickListener {
                viewModel.handleEvent(EquipoEvent.UpdateEquipo(editTextEquipo.editText?.text.toString(),
                    editTextEstadio.editText?.text.toString(), viewModel.uiState.value?.equipo?.id))
            }
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        viewModel.handleEvent(EquipoEvent.GetEquipo(viewModel.uiState.value?.equipo?.nombre))
    }
}