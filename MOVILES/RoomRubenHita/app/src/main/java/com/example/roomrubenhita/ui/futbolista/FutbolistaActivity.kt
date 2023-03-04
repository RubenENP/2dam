package com.example.roomrubenhita.ui.futbolista

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.roomrubenhita.data.FutbolistaRoomDatabase
import com.example.roomrubenhita.data.Repository
import com.example.roomrubenhita.databinding.ActivityFutbolistaBinding
import com.example.roomrubenhita.domain.modelo.Futbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.AddFutbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.DeleteFutbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.GetFutbolistas
import timber.log.Timber


class FutbolistaActivity : AppCompatActivity(){

    private lateinit var binding: ActivityFutbolistaBinding

    private val viewModel: FutbolistaViewModel by viewModels {
        FutbolistaViewModelFactory(
            AddFutbolista(Repository(FutbolistaRoomDatabase.getDatabase(this).futbolistaDao())),
            GetFutbolistas(Repository(FutbolistaRoomDatabase.getDatabase(this).futbolistaDao())),
        )
    }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.extras?.isEmpty == false) {
            viewModel.handleEvent(
                FutbolistaEvent.GetFutbolista(intent.getStringExtra("futbolista").toString())
            )
        }

        binding = ActivityFutbolistaBinding.inflate(layoutInflater).apply {
            setContentView(root)

            //Añadir un futbolista
            anyadirButton.setOnClickListener {

                val posicion: String = when(radioGroup.checkedRadioButtonId){
                    radioPortero.id -> "Portero"
                    radioDefensa.id -> "Defensa"
                    radioMediocentro.id -> "Mediocentro"
                    radioDelantero.id -> "Delantero"
                    else -> "null"
                }

                val futbolista = Futbolista(tvNombre.editText?.text.toString(),
                    posicion,
                    tvBalonesDeOro.editText?.text.toString().toInt(),
                    tvChampionsGanadas.editText?.text.toString().toInt())

                viewModel.handleEvent(FutbolistaEvent.AddFutbolista(futbolista))
            }

            observeState(this)
        }
    }

    private fun observeState(binding: ActivityFutbolistaBinding){
        binding.apply {
            viewModel.uiState.observe(this@FutbolistaActivity) { state ->
                state.error?.let {
                    Toast.makeText(this@FutbolistaActivity, it, Toast.LENGTH_SHORT).show()
                }

                state.futbolista?.let {
                    tvNombre.editText?.text = Editable.Factory.getInstance().newEditable(it.nombre)
                    tvBalonesDeOro.editText?.text = Editable.Factory.getInstance().newEditable(it.balonesDeOro.toString())
                    tvChampionsGanadas.editText?.text = Editable.Factory.getInstance().newEditable(it.championsGanadas.toString())
                    when(it.posicion){
                        "Portero" -> radioPortero.isChecked = true
                        "Defensa" -> radioDefensa.isChecked = true
                        "Mediocentro" -> radioMediocentro.isChecked = true
                        "Delantero" -> radioDelantero.isChecked = true
                    }
                }
            }
        }
    }
}