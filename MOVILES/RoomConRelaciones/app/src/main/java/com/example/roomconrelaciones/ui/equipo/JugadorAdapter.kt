package com.example.roomconrelaciones.ui.equipo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomconrelaciones.R
import com.example.roomconrelaciones.databinding.ItemEquiposBinding
import com.example.roomconrelaciones.databinding.ItemJugadorBinding
import com.example.roomconrelaciones.domain.modelo.Equipo
import com.example.roomconrelaciones.domain.modelo.Jugador

class JugadorAdapter(
    private val actions: Actions,
): ListAdapter<Jugador, JugadorAdapter.ItemViewholder>(DiffCallback()){
    interface Actions {
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

        fun bind(item: Jugador) = with(binding) {
            tvNombreJugador.text = item.nombre
            tvPosicion.text = item.posicion
            tvTitulos.text = item.titulos.toString()

            deleteButton.setOnClickListener {
                actions.borrarJugador(item)
            }
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<Jugador>() {
    override fun areItemsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
        return oldItem.nombre == newItem.nombre
    }

    override fun areContentsTheSame(oldItem: Jugador, newItem: Jugador): Boolean {
        return oldItem == newItem
    }
}