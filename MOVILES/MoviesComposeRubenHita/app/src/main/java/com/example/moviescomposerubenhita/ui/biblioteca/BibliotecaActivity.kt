package com.example.moviescomposerubenhita.ui.biblioteca

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.moviescomposerubenhita.R
import com.example.moviescomposerubenhita.domain.model.Movie
import com.example.moviescomposerubenhita.ui.main.MoviesRecyclerView
import com.example.moviescomposerubenhita.ui.main.SeriesRecyclerView
import com.example.moviescomposerubenhita.ui.main.theme.MoviesComposeRubenHitaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BibliotecaScreen(viewModel: BibliotecaViewModel, mContext: Context) {
    viewModel.handleEvent(BibliotecaEvent.GetAll())

    viewModel.uiState.value.error?.let {
        Toast.makeText(mContext, it, Toast.LENGTH_LONG).show()
    }

    Column(modifier = Modifier
        .padding(PaddingValues(15.dp, 100.dp, 0.dp, 0.dp))) {
        Content(movies = viewModel.uiState.value.favoriteList, textValue = "Peliculas favoritas")
        Content(movies = viewModel.uiState.value.seeLaterList, textValue = "Ver mas tarde")
    }
}

@Composable
fun Content(
    movies: List<Movie>,
    textValue: String
) {
    Column() {
        Text(
            text = textValue,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.punch_font))
        )

        MoviesRecyclerView(
            movies = movies
        )
    }
}
