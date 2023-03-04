package com.example.navigationrubenhita.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.navigationrubenhita.R
import com.example.navigationrubenhita.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.findNavController()

            bottomNavigation.menu.findItem(R.id.page_inicio).isChecked = true


            bottomNavigation.setOnItemSelectedListener { item ->
                when (bottomNavigation.selectedItemId){
                    R.id.page_inicio ->{
                        when (item.itemId) {
                            R.id.page_partidos -> {
                                navController.navigate(R.id.main_to_partidos)
                                true
                            }
                            R.id.page_goleadores -> {
                                navController.navigate(R.id.main_to_grupos)
                                true
                            }
                            else -> {
                                false
                            }
                        }
                    }

                    R.id.page_partidos ->{
                        when (item.itemId) {
                            R.id.page_inicio -> {
                                navController.navigate(R.id.partidos_to_main)
                                true
                            }
                            R.id.page_goleadores -> {
                                navController.navigate(R.id.partidos_to_grupos)
                                true
                            }
                            else -> {
                                false
                            }
                        }
                    }

                    R.id.page_goleadores ->{
                        when (item.itemId) {
                            R.id.page_partidos -> {
                                navController.navigate(R.id.grupos_to_partidos)
                                true
                            }
                            R.id.page_inicio -> {
                                navController.navigate(R.id.grupos_to_main)
                                true
                            }
                            else -> {
                                false
                            }
                        }
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }
}