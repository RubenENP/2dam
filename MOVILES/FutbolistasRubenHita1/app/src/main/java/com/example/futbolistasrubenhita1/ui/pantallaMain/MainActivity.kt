package com.example.futbolistasrubenhita1.ui.pantallaMain

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.futbolistasrubenhita1.databinding.ActivityMainBinding
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.AddFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.DeleteFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.GetFutbolistas
import com.example.futbolistasrubenhita1.utils.StringProvider
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddFutbolista(),
            GetFutbolistas(),
            DeleteFutbolista(),
        )
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel.uiState.observe(this@MainActivity) { state ->
                state.error?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.errorMostrado()
                }

                if (state.error == null)
                    mainText.text = state.futbolista.toString()
            }

            anyadirButton.setOnClickListener {
                val nombre: String = tvNombre?.editText?.text.toString();
                var posicion = ""
                when (radioGroup.checkedRadioButtonId) {
                    radioPortero.id -> posicion = radioPortero.text.toString()
                    radioDefensa.id -> posicion = radioDefensa.text.toString()
                    radioMediocentro.id -> posicion = radioMediocentro.text.toString()
                    radioDelantero.id -> posicion = radioDelantero.text.toString()
                    else -> Toast.makeText(
                        this@MainActivity,
                        "Selecciona una posicion",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val balonesDeOro: Int = Integer.parseInt(tvBalonesDeOro?.editText?.text.toString())
                val championsGanadas: Int =
                    Integer.parseInt(tvBalonesDeOro?.editText?.text.toString())

                val futbolista = Futbolista(nombre, posicion, balonesDeOro, championsGanadas)

                viewModel.addFutbolista(futbolista)
            }

            var index = 0
            imageButtonRight.setOnClickListener {
                if (index < viewModel.getFutbolistasSize() - 1) {
                    index++
                    mainText.text = viewModel.getFutbolistas(index)
                }
            }

            imageButtonLeft.setOnClickListener {
                if (index > 0) {
                    index--
                    mainText.text = viewModel.getFutbolistas(index)
                }
            }

            //Añadir Futbolista
            anyadirButton.setOnClickListener {
                val nombre: String = tvNombre?.editText?.text.toString();
                var posicion = ""
                when (radioGroup.checkedRadioButtonId) {
                    radioPortero.id -> posicion = radioPortero.text.toString()
                    radioDefensa.id -> posicion = radioDefensa.text.toString()
                    radioMediocentro.id -> posicion = radioMediocentro.text.toString()
                    radioDelantero.id -> posicion = radioDelantero.text.toString()
                    else -> Toast.makeText(
                        this@MainActivity,
                        "Selecciona una posicion",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val balonesDeOro: Int = Integer.parseInt(tvBalonesDeOro?.editText?.text.toString())
                val championsGanadas: Int =
                    Integer.parseInt(tvBalonesDeOro?.editText?.text.toString())

                val futbolista = Futbolista(nombre, posicion, balonesDeOro, championsGanadas)

                viewModel.addFutbolista(futbolista)
            }

            //Borrar Futbolista
            imageButtonDelete.setOnClickListener {
                viewModel.deleteFutbolista(index)
                index--

                Toast.makeText(this@MainActivity, "Futbolista borrado", Toast.LENGTH_SHORT).show()

                if (index >= viewModel.getFutbolistasSize()){
                    mainText.text = viewModel.getFutbolistas(index-1)
                } else {
                    mainText.text = viewModel.getFutbolistas(index)
                }
            }
        }
    }
}