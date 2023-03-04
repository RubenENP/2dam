package com.example.examen1evrubenhita.ui.detalleEquipo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examen1evrubenhita.R
import com.example.examen1evrubenhita.databinding.ActivityDetalleEquipoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetalleEquipoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleEquipoBinding

    private val viewModel: DetallleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.handleEvent(DetalleEquipoEvent.GetEquipoWithComponentes(intent.getIntExtra("equipoId",0)))

        binding = ActivityDetalleEquipoBinding.inflate(layoutInflater)

        with(binding){
            setContentView(root)

            val adapter = ComponenteAdapter()

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@DetalleEquipoActivity)

            viewModel.uiState.observe(this@DetalleEquipoActivity){ state ->
                state.equipo?.let {
                    adapter.submitList(it.componentesList)
                    nombreEquipo?.setText(it.nombre)
                }

                state.error?.let {
                    Toast.makeText(this@DetalleEquipoActivity, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}