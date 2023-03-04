package com.example.futbolistasrubenhita1.ui.recycler

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futbolistasrubenhita1.R
import com.example.futbolistasrubenhita1.data.Repository
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.DeleteFutbolista
import com.example.futbolistasrubenhita1.domain.usescases.futbolistas.GetFutbolistas
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class RecyclerActivity : AppCompatActivity(){
    private fun click(nombre: String) {
        Snackbar.make(
            findViewById<RecyclerView>(R.id.rvFutbolistas), nombre, Snackbar.LENGTH_SHORT
        ).show()
    }

    private val viewModel: RecyclerViewModel by viewModels {
        RecyclerViewModelFactory(
            DeleteFutbolista(),
            GetFutbolistas(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        intent.extras?.let {
            val futbolista = it.getParcelable<Futbolista>("futbolista").toString()
            Timber.i("Futbolista: $futbolista")
            Timber.tag("Futbolista").i("Futbolista: $futbolista")
        }

        val futbolistaList = Repository.getFutbolistas()

        val recyclerViewFutbolistas = this.findViewById<RecyclerView>(R.id.rvFutbolistas)

        val adapter = FutbolistaAdapter(futbolistaList, this::click,
            object : FutbolistaAdapter.Actions {
                override fun onClickDelete(futbolista: Futbolista) {
                    viewModel.deleteFutbolista(futbolista)
                }
            },
        )

        futbolistaList.let {
            recyclerViewFutbolistas.adapter = adapter
            recyclerViewFutbolistas.layoutManager = LinearLayoutManager(this@RecyclerActivity)
        }

        viewModel.uiState.observe(this@RecyclerActivity){ state ->
            state.error?.let { error ->
                if (error != null){
                    Toast.makeText(this@RecyclerActivity, error, Toast.LENGTH_SHORT).show()
                    viewModel.errorMostrado()
                }
            }

            adapter.cambiarLista(state.listFutbolistas)
        }
    }

}