package com.example.mundialrubenhita.ui.registros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mundialrubenhita.R
import com.example.mundialrubenhita.databinding.FragmentRegistrosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrosFragment : Fragment() {
    private var _binding: FragmentRegistrosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrosBinding.inflate(inflater, container, false)

        setRecyclerView()

        main()

        return binding.root
    }

    private fun main() {
        viewModel.handleEvent(RegistrosEvent.GetRegistros)
    }

    private fun setRecyclerView() {
        val adapter = RegistroAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(layoutInflater.context)

        viewModel.uiState.observe(this@RegistrosFragment.viewLifecycleOwner) { state ->
            state.listaRegistros?.let {
                adapter.submitList(it)
                binding.sinRegistrosTv.visibility = View.GONE
            }

            state.error?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        main()
    }
}