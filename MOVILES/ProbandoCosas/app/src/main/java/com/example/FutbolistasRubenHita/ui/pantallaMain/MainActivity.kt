package com.example.ProbandoCosas.ui.pantallaMain

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ProbandoCosas.domain.modelo.Futbolista
import com.example.ProbandoCosas.domain.usecases.futbolistas.AddFutbolista
import com.example.ProbandoCosas.domain.usecases.futbolistas.DeleteFutbolista
import com.example.ProbandoCosas.domain.usecases.futbolistas.GetFutbolistas
import com.example.ProbandoCosas.utils.StringProvider
import com.example.probandocosas.databinding.ActivityMainBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel.uiState.observe(this@MainActivity) { state ->
                state.error?.let { error ->
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.errorMostrado()
                }
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

            anyadirButton.setOnClickListener {
                val nombre: String = editTextNombre.text.toString()
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
                val balonesDeOro: Int = Integer.parseInt(editTextBalonesDeOro.text.toString())
                val championsGanadas: Int =
                    Integer.parseInt(editTextChampionsGanadas.text.toString())

                val futbolista = Futbolista(nombre, posicion, balonesDeOro, championsGanadas)

                viewModel.addFutbolista(futbolista)

                Toast.makeText(
                    this@MainActivity,
                    futbolista.nombre + " añadido",
                    Toast.LENGTH_SHORT
                ).show()

            }

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