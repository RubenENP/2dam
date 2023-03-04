package com.example.roomrubenhita.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomrubenhita.R
import com.example.roomrubenhita.databinding.ItemFutbolistaBinding
import com.example.roomrubenhita.domain.modelo.Futbolista

class FutbolistaAdapter(
    private val actions: Actions,
): ListAdapter<Futbolista, FutbolistaAdapter.ItemViewholder>(DiffCallback()) {

    interface Actions {
        fun verFutbolista(nombreFutbolista: String)
        fun borrarFutbolista(futbolista: Futbolista)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_futbolista, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemFutbolistaBinding.bind(itemView)

        fun bind(item: Futbolista) = with(binding) {
            tvNombre.text = item.nombre
            tvPosicion.text = item.posicion

            verJugador.setOnClickListener {
                actions.verFutbolista(item.nombre)
            }

            deleteButton.setOnClickListener {
                actions.borrarFutbolista(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Futbolista>() {
        override fun areItemsTheSame(oldItem: Futbolista, newItem: Futbolista): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: Futbolista, newItem: Futbolista): Boolean {
            return oldItem == newItem
        }
    }
}