package com.example.examen1evrubenhita.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.examen1evrubenhita.R
import com.example.examen1evrubenhita.databinding.ActivityMainBinding
import com.example.examen1evrubenhita.domain.modelo.Equipo
import com.example.examen1evrubenhita.ui.addEquipo.AddEquipoActivity
import com.example.examen1evrubenhita.ui.detalleEquipo.DetalleEquipoActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.handleEvent(MainEvent.GetEquipos)

        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding){
            setContentView(root)

            val adapter = EquipoAdapter(
                object :EquipoAdapter.Actions{
                    override fun verEquipo(idEquipo: Int) {
                        val intent = Intent(this@MainActivity, DetalleEquipoActivity::class.java)
                        intent.putExtra("equipoId", idEquipo)
                        startActivity(intent)
                    }

                    override fun borrarEquipo(equipo: Equipo) {
                       viewModel.handleEvent(MainEvent.BorrarEquipo(equipo))
                    }
                }
            )

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            viewModel.uiState.observe(this@MainActivity){ state ->
                state.equiposList?.let {
                    adapter.submitList(it)
                }

                state.error?.let {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
                }
            }

            addEquipo.setOnClickListener {
                val intent = Intent(this@MainActivity, AddEquipoActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(MainEvent.GetEquipos)
    }
}