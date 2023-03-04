package com.example.navigationrubenhita.ui.grupos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.navigationrubenhita.databinding.FragmentGruposBinding
import com.example.navigationrubenhita.ui.unGrupo.PartidosDeUnGrupoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class GruposFragment : Fragment() {
    private var _binding: FragmentGruposBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GruposViewModel by viewModels()

    var grupoId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGruposBinding.inflate(inflater, container, false)

        main()

        return binding.root
    }

    private fun main() {
        with(binding.generarGruposButton) {
            setOnClickListener {
                viewModel.handleEvent(GruposEvent.GenerarGrupos)
                this.visibility = View.GONE
            }
        }

        val adapter = GrupoAdapter(
            object : GrupoAdapter.Actions {
                override fun clickGrupo(id: Int) {
                    val intent = Intent(activity, PartidosDeUnGrupoActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                    grupoId = id
                }
            }
        )

        binding.recyclerView.adapter = adapter

        viewModel.uiState.observe(this.viewLifecycleOwner) { state ->
            state.allGrupos?.let {
                adapter.submitList(it)
            }

            state.error?.let {
                Toast.makeText(this@GruposFragment.context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleEvent(GruposEvent.DeleteGrupo(grupoId))
    }
}


