package com.example.relacionnmrubenhita.ui.verItems.consola

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.relacionnmrubenhita.R
import com.example.relacionnmrubenhita.databinding.ActivityConsolaBinding
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import com.example.relacionnmrubenhita.ui.Constantes
import com.example.relacionnmrubenhita.ui.addJugador.AddJugadorActivity
import com.example.relacionnmrubenhita.ui.main.MainActivity
import com.example.relacionnmrubenhita.ui.main.MainEvent
import com.example.relacionnmrubenhita.ui.verItems.JugadorAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsolaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsolaBinding

    private val viewModel : ConsolaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConsolaBinding.inflate(layoutInflater)

        with(binding){
            setContentView(root)

            viewModel.handleEvent(ConsolaEvent.GetUnaConsola(intent.getIntExtra(Constantes.CONSOLA_ID, 0)))

            val adapter = JugadorAdapter(
                object : JugadorAdapter.Actions {
                    override fun borrarJugador(jugador: Jugador) {
                        viewModel.handleEvent(ConsolaEvent.BorrarJugador(jugador))
                    }
                }
            )

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@ConsolaActivity)

            viewModel.uiState.observe(this@ConsolaActivity){
                it.consola?.let { consola ->
                    editTextNombre.setText(consola.nombre)
                    editTextMarca.setText(consola.marca)
                    editTextPrecio.setText(consola.precio.toString())

                    adapter.submitList(consola.jugadoresList)
                }
            }

            addJugador.setOnClickListener {
                viewModel.uiState.value?.consola?.let {
                    intent = Intent(this@ConsolaActivity, AddJugadorActivity::class.java)
                    intent.putExtra(Constantes.CONSOLA_ID, it.id)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        viewModel.handleEvent(ConsolaEvent.GetUnaConsola(intent.getIntExtra(Constantes.CONSOLA_ID, 0)))
    }
}