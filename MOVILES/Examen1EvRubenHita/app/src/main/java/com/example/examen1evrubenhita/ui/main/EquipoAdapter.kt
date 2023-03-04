package com.example.examen1evrubenhita.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examen1evrubenhita.R
import com.example.examen1evrubenhita.databinding.ItemEquiposBinding
import com.example.examen1evrubenhita.domain.modelo.Equipo

class EquipoAdapter(
    private val actions: Actions,
) : ListAdapter<Equipo, EquipoAdapter.ItemViewholder>(DiffCallback()) {
    interface Actions {
        fun verEquipo(idEquipo: Int)
        fun borrarEquipo(equipo: Equipo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_equipos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemEquiposBinding.bind(itemView)

        fun bind(item: Equipo) = with(binding) {
            tvNombre.text = item.nombre
            tvNacionalidad.text = item.nacionalidad
            tvPuesto.text = item.puesto.toString()

            verComponentes.setOnClickListener {
                actions.verEquipo(item.id)
            }

            deleteButton.setOnClickListener {
                actions.borrarEquipo(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Equipo>() {
    override fun areItemsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
        return oldItem.nombre == newItem.nombre
    }

    override fun areContentsTheSame(oldItem: Equipo, newItem: Equipo): Boolean {
        return oldItem == newItem
    }
}