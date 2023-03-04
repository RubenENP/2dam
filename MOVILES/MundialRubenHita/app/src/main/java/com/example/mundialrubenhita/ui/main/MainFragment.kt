package com.example.mundialrubenhita.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.mundialrubenhita.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        viewModel.handleEvent(MainEvent.GetEquipos)

        val adapter = EquiposAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(layoutInflater.context)

        viewModel.uiState.observe(this.viewLifecycleOwner){ state ->
            state.listEquipos?.let {
                adapter.submitList(it)
            }

            state.error?.let {
                Toast.makeText(this@MainFragment.context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.load("https://brandemia.org/sites/default/files/inline/images/qatar-2022-logo-700x700.jpg")
    }
}