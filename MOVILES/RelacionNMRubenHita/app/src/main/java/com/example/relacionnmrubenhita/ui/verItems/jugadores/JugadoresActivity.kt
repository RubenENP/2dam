package com.example.relacionnmrubenhita.ui.verItems.jugadores

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.relacionnmrubenhita.databinding.ActivityConsolaBinding
import com.example.relacionnmrubenhita.databinding.ActivityJugadorBinding
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.ui.Constantes
import com.example.relacionnmrubenhita.ui.addConsola.AddConsolaActivity
import com.example.relacionnmrubenhita.ui.verItems.ConsolaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JugadoresActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJugadorBinding

    private val viewModel: JugadoresViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJugadorBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(JugadoresEvent.GetUnJugador(intent.getIntExtra(Constantes.JUGADOR_ID, 0)))

            val consolasAdapter = ConsolaAdapter(
                object : ConsolaAdapter.Actions{
                    override fun borrarConsola(consola: Consola){
                        viewModel.handleEvent(JugadoresEvent.DeleteConsola(consola))
                    }
                }
            )

            recyclerView.adapter = consolasAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@JugadoresActivity)

            viewModel.uiState.observe(this@JugadoresActivity) {
                it.jugador?.let { jugador ->
                    editTextNombre.setText(jugador.nombre)
                    editTextDinero.setText(jugador.dinero.toString())

                    consolasAdapter.submitList(jugador.consolasList)

                    addConsola.setOnClickListener {
                        val intent = Intent(this@JugadoresActivity, AddConsolaActivity::class.java)
                        intent.putExtra(Constantes.JUGADOR_ID, jugador.id)
                        startActivity(intent)
                    }
                }

                it.error?.let { error ->
                    Toast.makeText(this@JugadoresActivity, error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        viewModel.handleEvent(JugadoresEvent.GetUnJugador(intent.getIntExtra(Constantes.JUGADOR_ID,0)))
    }
}