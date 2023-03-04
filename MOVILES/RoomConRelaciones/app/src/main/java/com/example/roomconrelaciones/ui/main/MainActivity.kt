package com.example.roomconrelaciones.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomconrelaciones.R
import com.example.roomconrelaciones.databinding.ActivityMainBinding
import com.example.roomconrelaciones.domain.common.Constantes
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.ui.addEquipo.AddEquipoActivity
import com.example.roomconrelaciones.ui.equipo.EquipoActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(MainEvent.GetAllEquipos)

            val adapter = EquipoAdapter(
                object:EquipoAdapter.Actions {
                    override fun verEquipo(nombreEquipo: String) {
                        val intent = Intent(this@MainActivity, EquipoActivity::class.java)
                        intent.putExtra(Constantes.EQUIPO, nombreEquipo)
                        startActivity(intent)
                    }

                    override fun borrarEquipo(equipo: Equipo) {
                        viewModel.handleEvent(MainEvent.DeleteEquipo(equipo))
                    }
                }
            )

            recyclerViewEquipos.adapter = adapter
            recyclerViewEquipos.layoutManager = LinearLayoutManager(this@MainActivity)

            viewModel.uiState.observe(this@MainActivity){
                if (it.error != null){
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                } else{
                    adapter.submitList(it.listEquipos)
                }
            }

            addEquipo.setOnClickListener {
                val intent = Intent(this@MainActivity, AddEquipoActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onPostResume() {
        super.onResume()

        viewModel.handleEvent(MainEvent.GetAllEquipos)
    }
}