package com.example.moviescomposerubenhita.ui.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.moviescomposerubenhita.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Biblioteca : Screen("biblioteca", R.string.biblioteca, Icons.Filled.List)
    object Actores : Screen("actores", R.string.actores, Icons.Filled.Person)
    object Main : Screen("main", R.string.main, Icons.Filled.Home)
}
