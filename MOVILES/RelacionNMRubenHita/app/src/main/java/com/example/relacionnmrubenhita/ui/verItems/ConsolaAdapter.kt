package com.example.relacionnmrubenhita.ui.verItems

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.relacionnmrubenhita.R
import com.example.relacionnmrubenhita.databinding.ItemConsola2Binding
import com.example.relacionnmrubenhita.databinding.ItemJugador2Binding
import com.example.relacionnmrubenhita.domain.modelo.Consola
import com.example.relacionnmrubenhita.domain.modelo.Jugador

class ConsolaAdapter(
    private val actions: Actions,
):ListAdapter<Consola, ConsolaAdapter.ItemViewholder>(DiffCallbackk()) {
    interface Actions{
        fun borrarConsola(consola: Consola)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_consola_2, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemConsola2Binding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: Consola) = with(binding) {
            tvNombre.text = item.nombre
            tvMarca.text = item.marca
            tvPrecio.text = item.precio.toString()

            deleteButton.setOnClickListener {
                actions.borrarConsola(item)
            }
        }
    }
}


class DiffCallbackk : DiffUtil.ItemCallback<Consola>() {
    override fun areItemsTheSame(oldItem: Consola, newItem: Consola): Boolean {
        return oldItem.nombre == newItem.nombre
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Consola, newItem: Consola): Boolean {
        return oldItem == newItem
    }
}