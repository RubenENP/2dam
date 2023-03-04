package com.example.moviescomposerubenhita.ui.main;

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.moviescomposerubenhita.common.Constantes
import com.example.moviescomposerubenhita.domain.model.Movie
import com.example.moviescomposerubenhita.R
import com.example.moviescomposerubenhita.domain.model.Serie

@Composable
fun MainScreen(paddingValues: PaddingValues, movies: List<Movie>, series: List<Serie>) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(15.dp, 100.dp, 0.dp, 0.dp))
    ) {
        Content(movies, null, "Peliculas recomendadas")
        Content(null, series, "Series recomendadas")
    }
}

@Composable
@Preview(device = Devices.PIXEL_4_XL)
fun PreviewView() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val movies = listOf(
            Movie("Prueba de pelicula con titulo largo", "/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg",0,0),
            Movie("owow", "/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg",0,0)
        )

        Column(
            modifier = Modifier
                .padding(PaddingValues(15.dp, 100.dp, 0.dp, 0.dp)),
        ) {
            Content(movies, null, "Peliculas recomendadas")
            Content(movies, null, "Series recomendadas")
        }
    }
}

@Composable
fun Content(
    movies: List<Movie>?,
    series: List<Serie>?,
    textValue: String
) {
    Column() {
        Text(
            text = textValue,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.punch_font))
        )

        if (movies != null) {
            MoviesRecyclerView(
                movies = movies
            )
        } else if (series != null) {
            SeriesRecyclerView(
                series = series
            )
        }
    }
}


