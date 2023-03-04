package com.example.moviescomposerubenhita.ui.actores

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviescomposerubenhita.common.Constantes
import com.example.moviescomposerubenhita.domain.model.Actor

@Composable
fun ActoresActivity(actorsViewModel: ActoresViewModel, mContext: Context) {
    actorsViewModel.handleEvent(ActorsEvent.GetAll())
    Content(actorsViewModel = actorsViewModel, mContext)
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
private fun Content(
    actorsViewModel: ActoresViewModel,mContext: Context
) {
    actorsViewModel.uiState.value.error?.let {
        Toast.makeText(mContext, it, Toast.LENGTH_LONG).show()
    }
    actorsViewModel.uiState.value.listActors?.let {
        LazyColumn(modifier = Modifier
            .padding(PaddingValues(15.dp, 100.dp, 0.dp, 0.dp))) {
            items(
                items = it,
                itemContent = {
                    PersonaListItem(actor = it)
                }
            )
        }
    }
}

@Composable
fun PersonaListItem(actor: Actor) {
    Card(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 8.dp)
            .width(220.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                androidx.compose.material3.Text(text = actor.name)
                AsyncImage(
                    model = Constantes.BASE_IMG_URL + actor.profile_path,
                    contentDescription = "image",
                    modifier = Modifier
                        .height(100.dp)
                        .width(200.dp)
                )
            }
        }
    }
}
