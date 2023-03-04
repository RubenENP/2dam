package com.example.mundialrubenhita.ui.porra

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mundialrubenhita.databinding.FragmentPorraBinding
import com.example.mundialrubenhita.ui.apuesta.ApuestaActivity
import com.example.mundialrubenhita.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartidosFragment : Fragment() {
    private var _binding: FragmentPorraBinding?=null
    private val binding get() = _binding!!

    private val viewModel: PorraViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPorraBinding.inflate(inflater, container, false)

        setRecyclerView()

        main()

        return binding.root
    }

    private fun main() {
        viewModel.handleEvent(PorraEvent.GetPartidos)
        viewModel.handleEvent(PorraEvent.GetDinero)

        binding.reloadButton.setOnClickListener {
            viewModel.handleEvent(PorraEvent.ReloadPartidos)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setRecyclerView() {
        val adapter = PartidoAdapter(
            object:PartidoAdapter.Actions {
                override fun verMas(idPartido: Int) {
                    val intent = Intent(activity, ApuestaActivity::class.java)
                    intent.putExtra("partidoId", idPartido)
                    startActivity(intent)
                }
            }
        )

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(layoutInflater.context)

        viewModel.uiState.observe(this@PartidosFragment.viewLifecycleOwner){ state ->
            state.partidos?.let {
                adapter.submitList(it)
            }

            state.dinero?.let {
                binding.dineroTv.text = "Tu dinero: $it €"
            }

            state.error?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        main()
    }
}