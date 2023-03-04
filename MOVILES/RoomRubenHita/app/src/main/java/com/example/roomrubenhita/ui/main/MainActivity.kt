package com.example.roomrubenhita.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomrubenhita.R
import com.example.roomrubenhita.data.FutbolistaRoomDatabase
import com.example.roomrubenhita.data.Repository
import com.example.roomrubenhita.databinding.ActivityFutbolistaBinding
import com.example.roomrubenhita.databinding.ActivityMainBinding
import com.example.roomrubenhita.domain.modelo.Futbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.DeleteFutbolista
import com.example.roomrubenhita.domain.usescases.futbolistas.GetFutbolistas
import com.example.roomrubenhita.ui.futbolista.FutbolistaActivity
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            GetFutbolistas(Repository(FutbolistaRoomDatabase.getDatabase(this).futbolistaDao())),
            DeleteFutbolista(Repository(FutbolistaRoomDatabase.getDatabase(this).futbolistaDao())),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            viewModel.handleEvent(MainEvent.GetAllFutbolistas)

            val adapter = FutbolistaAdapter(
                object : FutbolistaAdapter.Actions {
                    override fun verFutbolista(nombreFutbolista: String) {
                        val intent = Intent(this@MainActivity, FutbolistaActivity::class.java)
                        intent.putExtra(Constantes.FUTBOLISTA, nombreFutbolista)
                        startActivity(intent)
                    }

                    override fun borrarFutbolista(futbolista: Futbolista) {
                        viewModel.handleEvent(MainEvent.DeleteFutbolista(futbolista))
                    }
                }
            )

            rvFutbolistas.adapter = adapter
            rvFutbolistas.layoutManager = LinearLayoutManager(this@MainActivity)


            viewModel.uiState.observe(this@MainActivity) { state ->
                state.listFutbolistas.let {
                    adapter.submitList(state.listFutbolistas)
                }

                state.error.let {
                    Timber.tag("TAG:::").e(it)
                }
            }

            binding.floatingActionButton.setOnClickListener {
                val intent = Intent(this@MainActivity, FutbolistaActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.handleEvent(MainEvent.GetAllFutbolistas)
    }
}