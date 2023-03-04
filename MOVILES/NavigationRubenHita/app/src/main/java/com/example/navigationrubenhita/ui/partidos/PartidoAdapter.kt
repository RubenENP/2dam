package com.example.navigationrubenhita.ui.partidos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationrubenhita.R
import com.example.navigationrubenhita.databinding.CardPartidosBinding
import com.example.navigationrubenhita.domain.modelo.Partido

class PartidoAdapter(
    private val actions: Actions,
) : ListAdapter<Partido, PartidoAdapter.ItemViewholder>(DiffCallback()) {
    interface Actions {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_partidos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardPartidosBinding.bind(itemView)

        fun bind(item: Partido) = with(binding) {
            equipoA.text = item.equipoA.nombre
            equipoB.text = item.equipoB.nombre
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<Partido>() {
    override fun areItemsTheSame(oldItem: Partido, newItem: Partido): Boolean {
        return oldItem.equipoA == newItem.equipoA
    }

    override fun areContentsTheSame(oldItem: Partido, newItem: Partido): Boolean {
        return oldItem == newItem
    }
}