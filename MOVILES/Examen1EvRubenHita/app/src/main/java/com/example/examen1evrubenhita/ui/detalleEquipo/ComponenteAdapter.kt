package com.example.examen1evrubenhita.ui.detalleEquipo

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.graphics.drawable.DrawableContainer
import android.graphics.drawable.Icon
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.result
import com.example.examen1evrubenhita.R
import com.example.examen1evrubenhita.databinding.ItemComponentesBinding
import com.example.examen1evrubenhita.domain.modelo.Componente
import java.io.File
import java.io.FileInputStream
import kotlin.io.path.Path


class ComponenteAdapter: ListAdapter<Componente, ComponenteAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_componentes, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemComponentesBinding.bind(itemView)

        fun bind(item: Componente) = with(binding) {
            tvNombre.text = item.nombre

            if (item.tipo == "Entrenador"){
                iconoTipo.text = "Ent"
            } else if (item.tipo == "Jugador"){
                iconoTipo.text = "Jug"
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Componente>() {
    override fun areItemsTheSame(oldItem: Componente, newItem: Componente): Boolean {
        return oldItem.nombre == newItem.nombre
    }

    override fun areContentsTheSame(oldItem: Componente, newItem: Componente): Boolean {
        return oldItem == newItem
    }
}