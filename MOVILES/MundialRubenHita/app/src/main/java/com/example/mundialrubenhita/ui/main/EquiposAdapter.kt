package com.example.mundialrubenhita.ui.main

import android.animation.LayoutTransition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import coil.load
import com.example.mundialrubenhita.R
import com.example.mundialrubenhita.databinding.ItemEquipoBinding
import com.example.mundialrubenhita.domain.modelo.Equipo

class EquiposAdapter : ListAdapter<Equipo, EquiposAdapter.ItemViewholder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_equipo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemEquipoBinding.bind(itemView)

        fun bind(item: Equipo) = with(binding) {
            nombreEquipo.text = item.nombre
            banderaImage.load(item.escudoImg)

            layout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

            alineacionImg.load(item.alineacionImg)
            ranking.text = "Ranking de la FIFA: "+item.rankingFifa

            card.setOnClickListener {

                val v: Int = if (detalles.visibility == View.VISIBLE){
                    View.GONE
                } else{
                    View.VISIBLE
                }

                TransitionManager.beginDelayedTransition(layout, AutoTransition())
                detalles.visibility = v
            }
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
