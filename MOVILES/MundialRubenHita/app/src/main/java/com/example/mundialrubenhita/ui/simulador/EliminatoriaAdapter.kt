package com.example.mundialrubenhita.ui.simulador

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mundialrubenhita.R
import com.example.mundialrubenhita.databinding.ItemEliminatoriaBinding
import com.example.mundialrubenhita.domain.modelo.PartidoEliminatoria

class EliminatoriaAdapter () : ListAdapter<PartidoEliminatoria, EliminatoriaAdapter.ItemViewholder>(DiffCallbac()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_eliminatoria, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemEliminatoriaBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: PartidoEliminatoria) = with(binding) {
            tipoTv.text = item.tipo.nombre
            equiposTv.text = "${item.equipo1.nombre} vs ${item.equipo2.nombre}"
            ganadorTv.text = "Ganador: "+item.ganador.nombre
        }
    }
}

class DiffCallbac : DiffUtil.ItemCallback<PartidoEliminatoria>() {
    override fun areItemsTheSame(oldItem: PartidoEliminatoria, newItem: PartidoEliminatoria): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PartidoEliminatoria, newItem: PartidoEliminatoria): Boolean {
        return oldItem == newItem
    }
}