package com.example.navigationrubenhita.ui.unGrupo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.navigationrubenhita.R
import com.example.navigationrubenhita.databinding.CardPartidosBinding
import com.example.navigationrubenhita.databinding.ItemEquipoDeGrupoBinding
import com.example.navigationrubenhita.databinding.ItemPartidoBinding
import com.example.navigationrubenhita.domain.modelo.Equipo
import com.example.navigationrubenhita.domain.modelo.Partido
import com.example.navigationrubenhita.ui.partidos.PartidoAdapter

class PartidoDeUnGrupoAdapter(

):ListAdapter<Equipo, PartidoDeUnGrupoAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartidoDeUnGrupoAdapter.ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_equipo_de_grupo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PartidoDeUnGrupoAdapter.ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemEquipoDeGrupoBinding.bind(itemView)

        fun bind(item: Equipo) = with(binding) {
            tvEquipo.text = item.nombre
            bandera.load(item.escudoImg)
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