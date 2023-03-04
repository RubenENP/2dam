package com.example.moviescomposerubenhita.ui.searchedMovies

import android.content.Context
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.moviescomposerubenhita.R
import com.example.moviescomposerubenhita.common.Constantes
import com.example.moviescomposerubenhita.domain.model.Movie
import com.example.moviescomposerubenhita.ui.searchedMovies.ui.theme.MoviesComposeRubenHitaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchedMovieList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesComposeRubenHitaTheme {
                val listViewModel: SearchMovieListViewModel = hiltViewModel()
                val name = intent.getStringExtra("nombre")
                name?.let { SearchEvent.SearchMovie(it) }?.let { listViewModel.handleEvent(it) }
                ScaffoldSimple(this, listViewModel, LocalContext.current)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScaffoldSimple(activity: SearchedMovieList, listViewModel: SearchMovieListViewModel, mContext: Context) {
    val snackbarHostState = SnackbarHostState()

    val state by listViewModel.uiState.collectAsState()

    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Constantes.LISTA_DE_PELICULAS) },
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = Constantes.SALIR
                        )
                    }
                },
            )
        },
        content = {
            state.error?.let {
                scope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(it)
                    listViewModel.handleEvent(SearchEvent.LimpiarError())
                }
            }

            if (state.loading) {
                CircularProgressIndicator()
            } else if (state.listaPeliculas != null) {
                Content(it, listViewModel, state.listaPeliculas!!, mContext)
            }
        }
    )
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    listViewModel: SearchMovieListViewModel,
    movies: List<Movie>,
    mContext: Context
) {

    LazyColumn(contentPadding = paddingValues) {
        items(
            items = movies,
            itemContent = {
                PersonaListItem(movie = it, listViewModel = listViewModel, mContext=mContext)
            }
        )
    }
}

@Composable
fun PersonaListItem(movie: Movie, listViewModel: SearchMovieListViewModel, mContext: Context) {
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
                Text(text = movie.nombre)
                AsyncImage(
                    model = Constantes.BASE_IMG_URL + movie.image,
                    contentDescription = "image",
                    modifier = Modifier
                        .height(100.dp)
                        .width(200.dp)
                )
                Row() {
                    IconButton(onClick = {
                        listViewModel.handleEvent(SearchEvent.InsertFavorite(movie))
                        Toast.makeText(mContext,"Añadido a favoritos",Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = "favourite")
                    }

                    IconButton(onClick = {
                        listViewModel.handleEvent(
                            SearchEvent.InsertSeeLater(
                                movie
                            )
                        )
                        Toast.makeText(mContext,"Añadido a ver mas tarde",Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_time),
                            contentDescription = "see later"
                        )
                    }
                }
            }
        }
    }
}