package com.example.mundialrubenhita.ui.simulador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mundialrubenhita.R
import com.example.mundialrubenhita.databinding.ItemPartidoBinding
import com.example.mundialrubenhita.databinding.ItemSimpleEquipoBinding
import com.example.mundialrubenhita.domain.modelo.Equipo
import com.example.mundialrubenhita.domain.modelo.Partido
import com.example.mundialrubenhita.ui.porra.PartidoAdapter

class EquipoAdapterSimple() : ListAdapter<Equipo, EquipoAdapterSimple.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_simple_equipo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemSimpleEquipoBinding.bind(itemView)

        fun bind(item: Equipo) = with(binding) {
            nombreTv.text = item.nombre
            banderaImg.load(item.escudoImg)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Equipo>() {
    override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
        return oldItem == newItem
    }
}