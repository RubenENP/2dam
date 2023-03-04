package com.example.navigationrubenhita.ui.partidos

import android.media.metrics.Event
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationrubenhita.R
import com.example.navigationrubenhita.databinding.FragmentPartidosBinding
import com.example.navigationrubenhita.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartidosFragment : Fragment() {

    private var _binding : FragmentPartidosBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PartidosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPartidosBinding.inflate(inflater, container, false)

        viewModel.handleEvent(PartidosEvent.GetGrupos)
        if (viewModel.uiState.value?.gruposSize == 0){
            main()
        }

        return binding.root
    }

    private fun main(){

        viewModel.handleEvent(PartidosEvent.GetPartidos)

        val adapter = PartidoAdapter(
            object : PartidoAdapter.Actions{

            }
        )

        binding.recyclerView.adapter = adapter

        viewModel.uiState.observe(this@PartidosFragment.viewLifecycleOwner){
            it.partidos?.let { partidos ->
                adapter.submitList(partidos)
            }

            it.error?.let { error ->
                Toast.makeText(this@PartidosFragment.context, error, Toast.LENGTH_LONG).show()
            }
        }
    }
}