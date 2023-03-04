package com.example.moviescomposerubenhita.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviescomposerubenhita.common.Constantes
import com.example.moviescomposerubenhita.domain.model.Movie

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MoviesRecyclerView(
    movies: List<Movie>,
) {
    LazyRow(
        modifier = Modifier
            .height(150.dp)
    ) {
        items(
            items = movies,
            itemContent = {
                PersonaListItem(movie = it)
            }
        )
    }
}

@Composable
private fun PersonaListItem(movie: Movie) {
    Column() {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .size(120.dp, 68.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(corner = CornerSize(16.dp))

        ) {
            AsyncImage(
                model = Constantes.BASE_IMG_URL + movie.image,
                contentDescription = "image",
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = movie.nombre, modifier = Modifier
                .padding(10.dp)
                .width(120.dp)
                .fillMaxHeight()
        )
    }
}