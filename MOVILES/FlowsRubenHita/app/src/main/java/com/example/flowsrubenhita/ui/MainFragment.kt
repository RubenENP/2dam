package com.example.flowsrubenhita.ui

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
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flowsrubenhita.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container, false)

        main()

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun main() {

        val adapter = MovieAdapter()

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect { value ->
                    value.movies?.let {
                        adapter.submitList(it)
                    }

                    value.error?.let {
                        Toast.makeText(this@MainFragment.context, it, Toast.LENGTH_LONG).show()
                        Log.e("TAG:::",it)
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiError.collect{
                    Toast.makeText(this@MainFragment.context, it, Toast.LENGTH_LONG).show()
                    Log.e("TAG:::",it)
                }
            }
        }

        viewModel.handleEvent(MainEvent.GetUnaPeli(1))

    }
}