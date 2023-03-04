package com.example.moviesrubenhita.ui.buscador

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flowsrubenhita.ui.MovieAdapter
import com.example.moviesrubenhita.databinding.FragmentBuscadorBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BuscadorFragment : Fragment() {

    private var _binding: FragmentBuscadorBinding? = null

    private val viewModel: BuscadorViewModel by viewModels()
    private val binding get() = _binding!!

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuscadorBinding.inflate(inflater, container, false)

        val adapter = MovieAdapter()

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect{ state ->

                    state.moviesList?.let { movies ->
                        adapter.submitList(movies)
                    }

                    if(state.isLoading){
                        binding.loadingBar.visibility = View.VISIBLE
                    } else{
                        binding.loadingBar.visibility = View.GONE
                    }
                }
            }
        }


        setSearch()

        return binding.root
    }

    private fun setSearch() {
        binding.buscarButton.setOnClickListener {
            val name = binding.editTextName.text.toString()
            viewModel.handleEvent(BuscadorEvent.GetMoviesByName(name))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}