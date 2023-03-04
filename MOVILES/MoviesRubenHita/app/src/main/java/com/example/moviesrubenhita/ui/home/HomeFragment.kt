package com.example.moviesrubenhita.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flowsrubenhita.ui.MainEvent
import com.example.flowsrubenhita.ui.MovieAdapter
import com.example.moviesrubenhita.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        main()

        return binding.root
    }

    private fun main() {
        val adapter = MovieAdapter()

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { value ->
                    value.movies?.let {
                        adapter.submitList(it)
                    }

                    value.error?.let {
                        Toast.makeText(this@HomeFragment.context, it, Toast.LENGTH_LONG).show()
                    }

                    if (value.isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                    } else {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiError.collect {
                    Toast.makeText(this@HomeFragment.context, it, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.handleEvent(MainEvent.GetUnaPeli(1))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}