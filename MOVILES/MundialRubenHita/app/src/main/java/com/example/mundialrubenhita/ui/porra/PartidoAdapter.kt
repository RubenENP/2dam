package com.example.mundialrubenhita.ui.porra

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import coil.load
import com.example.mundialrubenhita.R
import com.example.mundialrubenhita.databinding.ItemEquipoBinding
import com.example.mundialrubenhita.databinding.ItemPartidoBinding
import com.example.mundialrubenhita.domain.modelo.Partido

class PartidoAdapter(
    private val actions: Actions
) : ListAdapter<Partido, PartidoAdapter.ItemViewholder>(DiffCallback()) {
    interface Actions{
        fun verMas(idPartido: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_partido, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPartidoBinding.bind(itemView)

        fun bind(item: Partido) = with(binding) {
            equipo1Nombre.text = item.equipoLocal.nombre
            equipo2Nombre.text = item.equipoVisitante.nombre

            verMasButton.setOnClickListener {
                actions.verMas(item.idPartido)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Partido>() {
    override fun areItemsTheSame(oldItem: Partido, newItem: Partido): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Partido, newItem: Partido): Boolean {
        return oldItem == newItem
    }
}