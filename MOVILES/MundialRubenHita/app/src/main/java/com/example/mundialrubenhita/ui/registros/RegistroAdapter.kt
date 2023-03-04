package com.example.mundialrubenhita.ui.registros

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mundialrubenhita.R
import com.example.mundialrubenhita.databinding.ItemPartidoBinding
import com.example.mundialrubenhita.databinding.ItemRegistroBinding
import com.example.mundialrubenhita.domain.modelo.Partido
import com.example.mundialrubenhita.domain.modelo.Registro
import com.example.mundialrubenhita.ui.porra.PartidoAdapter

class RegistroAdapter : ListAdapter<Registro, RegistroAdapter.ItemViewholder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_registro, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int): Unit = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemRegistroBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(item: Registro) = with(binding) {
            banderaLocalImg.load(item.partido?.equipoLocal?.escudoImg)
            banderaVisitanteImg.load(item.partido?.equipoVisitante?.escudoImg)

            equipoLocalTv.text = item.partido?.equipoLocal?.nombre
            equipoVisitanteTv.text = item.partido?.equipoVisitante?.nombre

            dineroTv.text = "Dinero: ${item.dinero}"
            resultadoTv.text = "Resultado: ${item.partido?.golesLocal}-${item.partido?.golesVisitante}"
            if (item.balance > 0) {
                graficaImg.load("https://images.emojiterra.com/google/noto-emoji/v2.034/128px/1f4c8.png")
                balanceTv.text = "Balance: +${item.balance}"
            } else{
                graficaImg.load("https://images.emojiterra.com/google/noto-emoji/v2.034/128px/1f4c9.png")
                balanceTv.text = "Balance: ${item.balance}"
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Registro>() {
    override fun areItemsTheSame(oldItem: Registro, newItem: Registro): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Registro, newItem: Registro): Boolean {
        return oldItem == newItem
    }
}