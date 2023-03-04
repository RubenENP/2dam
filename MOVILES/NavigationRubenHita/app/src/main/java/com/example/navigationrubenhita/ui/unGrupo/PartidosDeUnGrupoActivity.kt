package com.example.navigationrubenhita.ui.unGrupo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationrubenhita.databinding.ActivityPartidosDeUnGrupoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PartidosDeUnGrupoActivity : AppCompatActivity() {

    lateinit var binding: ActivityPartidosDeUnGrupoBinding

    private val viewModel: PartidosDeUnGrupoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = PartidoDeUnGrupoAdapter()

        binding = ActivityPartidosDeUnGrupoBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)

            recyclerView.adapter = adapter
        }

        viewModel.uiState.observe(this@PartidosDeUnGrupoActivity) { state ->
            state.grupo?.let {
                adapter.submitList(it.equipos)
            }

            state.error?.let {
                Toast.makeText(this@PartidosDeUnGrupoActivity, it, Toast.LENGTH_LONG).show()
            }
        }

        viewModel.handleEvent(PartidoDeUnGrupoEvent.SetGrupo(intent.getIntExtra("id", 0)))

        val callback = object :SwipeGesture(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.handleEvent(PartidoDeUnGrupoEvent.DeleteEquipo(viewHolder.absoluteAdapterPosition))
                adapter.notifyItemRemoved(viewHolder.absoluteAdapterPosition)
                if (adapter.currentList.size < 3){
                    viewModel.handleEvent(PartidoDeUnGrupoEvent.SetGrupoGanadores)
                    finish()
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}