package com.example.moviesrubenhita.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flowsrubenhita.ui.MovieAdapter
import com.example.moviesrubenhita.databinding.FragmentGenresBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenerosFragment : Fragment() {

    private var _binding: FragmentGenresBinding? = null

    val viewModel: GenerosViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenresBinding.inflate(inflater, container, false)

        setObserve()

        viewModel.handleEvent(GenerosEvent.GetGenres)

        return binding.root
    }

    private fun setObserve() {
        val adapter = MovieAdapter()

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect { state ->
                    state.genreList?.let { genres ->
                        val items = genres.map { it.name }.toTypedArray()
                        (binding.menu.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
                    }

                    state.movieList?.let {
                        adapter.submitList(it)
                    }

                    if (state.isLoading){
                        binding.loadingBar.visibility = View.VISIBLE
                    } else {
                        binding.loadingBar.visibility = View.GONE
                    }

                    state.error?.let {
                        Toast.makeText(this@GenerosFragment.context, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.button.setOnClickListener {
            val genre = binding.menu.editText.toString()
            viewModel.handleEvent(GenerosEvent.Filter(genre))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}