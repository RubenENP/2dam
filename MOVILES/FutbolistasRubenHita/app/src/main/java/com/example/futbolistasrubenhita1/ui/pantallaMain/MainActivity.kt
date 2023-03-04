package com.example.futbolistasrubenhita1.ui.pantallaMain

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.futbolistasrubenhita1.R
import com.example.futbolistasrubenhita1.databinding.ActivityMainBinding
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.AddFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.GetFutbolistas
import com.example.futbolistasrubenhita1.ui.recycler.RecyclerActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var temp: Int = 0

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AddFutbolista(),
            GetFutbolistas(),
        )
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            viewModel.uiState.observe(this@MainActivity) { state ->
                if (state.error == null){
                    Toast.makeText(this@MainActivity, state.futbolista.nombre+" Añadido", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_SHORT).show()
                    viewModel.errorMostrado()
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

            cambiarPantalla?.setOnClickListener {
                val intent = Intent(this@MainActivity, RecyclerActivity::class.java)
                intent.putExtra(getString(R.string.futbolista), viewModel.getAllFutbolistas().hashCode())
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) { // Here You have to save count value
        super.onSaveInstanceState(outState)
        Timber.i("onSaveInstanceState")

        outState.putInt("COUNT_KEY", temp)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)
        Timber.tag("::MyTag").i("onRestoreInstanceState")
        // Log.i("::MyTag", "onRestoreInstanceState")

        temp = savedInstanceState.getInt("COUNT_KEY")
    }
}