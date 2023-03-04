package com.example.futbolistasrubenhita1.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.futbolistasrubenhita1.R
import com.example.futbolistasrubenhita1.domain.modelo.Futbolista

class FutbolistaAdapter(
    private var futbolistas: List<Futbolista>,
    private val onClickBoton: (String) -> Unit,
    private val actions: Actions,
): RecyclerView.Adapter<FutbolistaViewHolder>() {

    interface Actions {
        fun onClickDelete(futbolista: Futbolista)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutbolistaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_futbolista, parent, false)
        return FutbolistaViewHolder(view)
    }

    override fun onBindViewHolder(holder: FutbolistaViewHolder, position: Int) {
        holder.render(futbolistas[position], actions)
    }

    override fun getItemCount(): Int = futbolistas.size

    fun cambiarLista(nuevaLista: List<Futbolista>) {
        futbolistas = nuevaLista
        notifyDataSetChanged()
    }
}

class FutbolistaViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun render(
        futbolista: Futbolista,
//        onClickBoton: (String) -> Unit,
        actions: FutbolistaAdapter.Actions,
    ) {

        view.findViewById<TextView>(R.id.tvNombre).text = futbolista.nombre
        view.findViewById<TextView>(R.id.tvPosicion).text = futbolista.posicion
        view.findViewById<ImageButton>(R.id.deleteButton).setOnClickListener {
            actions.onClickDelete(futbolista)
        }
    }
}