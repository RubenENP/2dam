package com.example.relacionnmrubenhita.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.relacionnmrubenhita.R
import com.example.relacionnmrubenhita.databinding.ItemJugadorBinding
import com.example.relacionnmrubenhita.domain.modelo.Jugador

class JugadorAdapter (
    private val actions: Actions,
) : ListAdapter<Jugador, JugadorAdapter.ItemViewholder>(DiffCallbac()) {
    interface Actions {
        fun verConsolas(idJugador: Int)
        fun borrarJugador(jugador: Jugador)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_jugador, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemJugadorBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: Jugador) = with(binding) {
            tvNombre.text = item.nombre
            tvDinero.text = item.dinero.toString()+" €"

            verConsolas.setOnClickListener {
                actions.verConsolas(item.id)
            }

            deleteButton.setOnClickListener {
                actions.borrarJugador(item)
            }
        }
    }
}


class DiffCallbac : DiffUtil.ItemCallback<Jugador>() {
    override fun areItemsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
        return oldItem.nombre == newItem.nombre
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
        return oldItem == newItem
    }
}