package com.example.mundialrubenhita.ui.simulador

import android.annotation.SuppressLint
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.mundialrubenhita.R
import com.example.mundialrubenhita.databinding.FragmentSimuladorBinding
import com.example.mundialrubenhita.domain.modelo.Equipo
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify

@AndroidEntryPoint
class SimuladorFragment : Fragment() {
    private var _binding: FragmentSimuladorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SimuladorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimuladorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        with(binding) {
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tabLayout.selectedTabPosition) {
                        0 -> {
                            gruposLayout.visibility = View.VISIBLE
                            eliminatoriaLayout.visibility = View.GONE
                        }

                        1 -> {
                            eliminatoriaLayout.visibility = View.VISIBLE
                            gruposLayout.visibility = View.GONE
                        }
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {}
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
            })

            mainGrupos()
            mainEliminatoria()
        }
    }

    private fun mainEliminatoria() {
        with(binding) {
            iniciarButton.setOnClickListener {
                if (viewModel.uiState.value?.equiposEliminatoria?.size == 16) {
                    viewModel.handleEvent(SimuladorEvent.EmpezarEliminatoria)

                    val eliminatoriaAdapter = EliminatoriaAdapter()

                    binding.recyclerViewEliminatoria.adapter = eliminatoriaAdapter
                    binding.recyclerViewEliminatoria.layoutManager =
                        LinearLayoutManager(layoutInflater.context)

                    viewModel.uiState.value!!.partidosEliminatoria?.let {
                        if (it.isNotEmpty()) {
                            eliminatoriaAdapter.submitList(it)
                        }
                    }
                } else{
                    Toast.makeText(this@SimuladorFragment.context, "Primero la fase de grupos jefe", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun mainGrupos() {
        val adapter = EquipoAdapterSimple()

        binding.recyclerViewGrupos.adapter = adapter
        binding.recyclerViewGrupos.layoutManager = LinearLayoutManager(layoutInflater.context)

        viewModel.uiState.observe(this@SimuladorFragment.viewLifecycleOwner) { state ->
            state.grupos?.get(state.indexGrupos)?.equipos.let {
                adapter.submitList(it)
            }

            state.error?.let {
                Toast.makeText(this@SimuladorFragment.context, it, Toast.LENGTH_LONG).show()
            }

            if (viewModel.uiState.value?.indexGrupos!! > 7) {
                binding.grupoTv.text = "Ya terminó la \nfase de grupos"
            }

        }

        viewModel.handleEvent(SimuladorEvent.CrearGrupos)

        val callback = object : SwipeGesture(this@SimuladorFragment.context) {
            @SuppressLint("SetTextI18n")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.handleEvent(SimuladorEvent.DeleteEquipo(viewHolder.absoluteAdapterPosition))
                adapter.notifyItemRemoved(viewHolder.absoluteAdapterPosition)
                if (adapter.currentList.size < 3) {
                    viewModel.handleEvent(SimuladorEvent.PasarDeGrupo)
                    adapter.submitList(viewModel.uiState.value?.let {

                        viewModel.uiState.value!!.grupos?.get(
                            it.indexGrupos
                        )?.equipos
                    })

                    binding.grupoTv.text = "Grupo ${
                        when (viewModel.uiState.value?.indexGrupos) {
                            0 -> "A"
                            1 -> "B"
                            2 -> "C"
                            3 -> "D"
                            4 -> "E"
                            5 -> "F"
                            6 -> "G"
                            7 -> "H"
                            else -> {
                                ""
                            }
                        }
                    }"
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewGrupos)
    }
}