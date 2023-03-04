package com.example.relacionnmrubenhita.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.relacionnmrubenhita.databinding.ActivityMainBinding
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador
import com.example.relacionnmrubenhita.ui.Constantes
import com.example.relacionnmrubenhita.ui.addConsola.AddConsolaActivity
import com.example.relacionnmrubenhita.ui.addJugador.AddJugadorActivity
import com.example.relacionnmrubenhita.ui.verItems.consola.ConsolaActivity
import com.example.relacionnmrubenhita.ui.verItems.jugadores.JugadoresActivity
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(MainEvent.GetAllConsolas)

            val consolaAdapter = ConsolaAdapter(
                object : ConsolaAdapter.Actions {
                    override fun verJugadores(idConsola: Int) {
                        val intent = Intent(this@MainActivity, ConsolaActivity::class.java)
                        intent.putExtra(Constantes.CONSOLA_ID, idConsola)
                        startActivity(intent)
                    }

                    override fun borrarConsola(consola: Consola) {
                        viewModel.handleEvent(MainEvent.DeleteConsola(consola))
                    }
                }
            )

            val jugadorAdapter = JugadorAdapter(
                object : JugadorAdapter.Actions {
                    override fun verConsolas(idJugador: Int) {
                        val intent = Intent(this@MainActivity, JugadoresActivity::class.java)
                        intent.putExtra(Constantes.JUGADOR_ID, idJugador)
                        startActivity(intent)
                    }

                    override fun borrarJugador(jugador: Jugador) {
                        viewModel.handleEvent(MainEvent.DeleteJugador(jugador))
                    }
                }
            )

            recyclerViewItems.layoutManager = LinearLayoutManager(this@MainActivity)

            viewModel.uiState.observe(this@MainActivity){
                it.consolasList?.let { list ->
                    recyclerViewItems.adapter = consolaAdapter
                    consolaAdapter.submitList(list)
                }
                it.jugadoresList?.let { list ->
                    recyclerViewItems.adapter = jugadorAdapter
                    jugadorAdapter.submitList(list)
                }
            }

            addItem.setOnClickListener {
                viewModel.uiState.value?.consolasList?.let {
                    val intent = Intent(this@MainActivity, AddConsolaActivity::class.java)
                    startActivity(intent)
                }
                viewModel.uiState.value?.jugadoresList?.let {
                    val intent = Intent(this@MainActivity, AddJugadorActivity::class.java)
                    startActivity(intent)
                }
            }

            tabLayoutListener()
        }
    }

    override fun onPostResume() {
        super.onPostResume()
        tabLayoutListener()
    }

    private fun tabLayoutListener(){
        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text.toString() == Constantes.CONSOLA){
                    viewModel.handleEvent(MainEvent.GetAllConsolas)
                }else{
                    viewModel.handleEvent(MainEvent.GetAllJugadores)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}