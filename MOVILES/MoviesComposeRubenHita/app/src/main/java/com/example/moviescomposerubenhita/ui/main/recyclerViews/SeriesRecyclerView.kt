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
import com.example.moviescomposerubenhita.domain.model.Serie

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SeriesRecyclerView(
    series: List<Serie>,
) {
    LazyRow(
        modifier = Modifier
            .height(150.dp)
    ) {
        items(
            items = series,
            itemContent = {
                PersonaListItem(serie = it)
            }
        )
    }
}

@Composable
private fun PersonaListItem(serie: Serie) {
    Column() {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .size(120.dp, 68.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(corner = CornerSize(16.dp))

        ) {
            AsyncImage(
                model = Constantes.BASE_IMG_URL + serie.image,
                contentDescription = "image",
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = serie.name, modifier = Modifier
                .padding(10.dp)
                .width(120.dp)
                .fillMaxHeight()
        )
    }
}