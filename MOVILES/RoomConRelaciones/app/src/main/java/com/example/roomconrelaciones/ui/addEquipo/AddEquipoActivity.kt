package com.example.roomconrelaciones.ui.addEquipo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.roomconrelaciones.databinding.ActivityAddequipoBinding
import com.example.roomconrelaciones.domain.common.Constantes
import com.example.roomconrelaciones.domain.modelo.Equipo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEquipoActivity :AppCompatActivity(){
    private lateinit var binding: ActivityAddequipoBinding

    private val viewModel: AddEquipoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddequipoBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            anyadirButton.setOnClickListener {
                val nombre = editTextNombre.text.toString()
                val estadio = editTextEstadio.text.toString()
                val equipo = Equipo(0, nombre, estadio, mutableListOf())
                viewModel.handleEvent(AddEquipoEvent.AddEquipo(equipo))
            }
        }

        viewModel.uiState.observe(this@AddEquipoActivity){
            if(it.error==null){
                Toast.makeText(this@AddEquipoActivity,it.equipo.nombre+Constantes.ANYADIDO, Toast.LENGTH_SHORT).show()
            } else if (it.error != Constantes.NULL){
                Toast.makeText(this@AddEquipoActivity, it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}