package com.example.navigationrubenhita.ui.grupos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.navigationrubenhita.R
import com.example.navigationrubenhita.databinding.CardGruposBinding
import com.example.navigationrubenhita.databinding.CardPartidosBinding
import com.example.navigationrubenhita.domain.modelo.Grupo
import com.example.navigationrubenhita.domain.modelo.Partido
import com.example.navigationrubenhita.ui.partidos.PartidoAdapter

class GrupoAdapter (
    private val actions: Actions,
): ListAdapter<Grupo, GrupoAdapter.ItemViewholder>(DiffCallback()) {
    interface Actions {
        fun clickGrupo(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_grupos, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }


    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardGruposBinding.bind(itemView)

        fun bind(item: Grupo) = with(binding) {
            equipoUno.text = item.equipos[0].nombre
            equipoDos.text = item.equipos[1].nombre
            equipoTres.text = item.equipos[2].nombre
            equipoCuatro.text = item.equipos[3].nombre

            banderaUno.load(item.equipos[0].escudoImg)
            banderaDos.load(item.equipos[1].escudoImg)
            banderaTres.load(item.equipos[2].escudoImg)
            banderaCuatro.load(item.equipos[3].escudoImg)

            itemView.setOnClickListener {
                actions.clickGrupo(item.id)

            }
        }
    }
}


class DiffCallback : DiffUtil.ItemCallback<Grupo>() {
    override fun areItemsTheSame(oldItem: Grupo, newItem: Grupo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Grupo, newItem: Grupo): Boolean {
        return oldItem == newItem
    }
}