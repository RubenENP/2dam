package com.example.relacionnmrubenhita.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.relacionnmrubenhita.R
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.databinding.ItemConsolaBinding

class ConsolaAdapter(
    private val actions: Actions,
) : ListAdapter<Consola, ConsolaAdapter.ItemViewholder>(DiffCallback()) {
    interface Actions {
        fun verJugadores(idConsola: Int)
        fun borrarConsola(consola: Consola)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_consola, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemConsolaBinding.bind(itemView)

        fun bind(item: Consola) = with(binding) {
            tvNombre.text = item.nombre
            tvMarca.text = item.marca
            tvPrecio.text = item.precio.toString()

            verJugadores.setOnClickListener {
                actions.verJugadores(item.id)
            }

            deleteButton.setOnClickListener {
                actions.borrarConsola(item)
            }
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<Consola>() {
    override fun areItemsTheSame(oldItem: Consola, newItem: Consola): Boolean {
        return oldItem.nombre == newItem.nombre
    }

    override fun areContentsTheSame(oldItem: Consola, newItem: Consola): Boolean {
        return oldItem == newItem
    }
}