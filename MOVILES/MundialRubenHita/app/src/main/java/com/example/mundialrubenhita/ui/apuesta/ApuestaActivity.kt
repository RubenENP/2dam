package com.example.mundialrubenhita.ui.apuesta

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.airbnb.lottie.parser.IntegerParser
import com.example.mundialrubenhita.databinding.ActivityApuestaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.absoluteValue

@AndroidEntryPoint
class ApuestaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityApuestaBinding

    private val viewModel: ApuestaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityApuestaBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            setSupportActionBar(myToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            viewModel.handleEvent(ApuestaEvent.GetPartido(intent.getIntExtra("partidoId", 0)))

            viewModel.uiState.observe(this@ApuestaActivity) { state ->
                state.partido?.let {
                    equipoLocaltv.text = it.equipoLocal.nombre
                    equipoVisitantetv.text = it.equipoVisitante.nombre

                    localRadioButton.text = it.equipoLocal.nombre
                    visitanteRadioButton.text = it.equipoVisitante.nombre
                }

                state.dinero?.let {
                    dineroTv.text = "Tu dinero: $it €"
                }

                state.error?.let {
                    Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
                }

                state.precioLocal?.let {
                    precioLocalTv.text = "$it €"
                }

                state.precioVisitante?.let {
                    precioVisitanteTv.text = "$it €"
                }

                state.precioEmpate?.let {
                    precioEmpateTv.text = "$it €"
                }

                state.apuestaModel?.let {
                    MaterialAlertDialogBuilder(this@ApuestaActivity)
                        .setTitle("Apuesta")
                        .setMessage(it.toString())
                        .setPositiveButton("Aceptar") { dialog, which ->
                            finish()
                        }
                        .show()
                }
            }
        }

        setApuestaButton()
    }

    private fun setApuestaButton() {
        with(binding) {
            apostarButton.setOnClickListener {
                if ((localRadioButton.isChecked || empateRadioButton.isChecked || visitanteRadioButton.isChecked)
                    && editTextApuesta.text.isNotEmpty()
                ) {
                    when (radioGroup.checkedRadioButtonId) {
                        localRadioButton.id -> {
                            viewModel.handleEvent(
                                ApuestaEvent.Apostar(
                                    editTextApuesta.text.toString().toInt(),
                                    1
                                )
                            )
                        }

                        empateRadioButton.id -> {
                            viewModel.handleEvent(
                                ApuestaEvent.Apostar(
                                    editTextApuesta.text.toString().toInt(),
                                    2
                                )
                            )
                        }

                        visitanteRadioButton.id -> {
                            viewModel.handleEvent(
                                ApuestaEvent.Apostar(
                                    editTextApuesta.text.toString().toInt(),
                                    3
                                )
                            )
                        }
                    }

                } else {
                    Toast.makeText(this@ApuestaActivity, "Apuesta Algo jefe", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}