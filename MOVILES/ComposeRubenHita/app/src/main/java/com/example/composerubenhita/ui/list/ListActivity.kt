package com.example.composerubenhita.ui.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composerubenhita.common.Constantes
import com.example.composerubenhita.domain.model.Persona
import com.example.composerubenhita.ui.list.theme.ComposeRubenHitaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRubenHitaTheme {
                MyApp(this)
            }
        }

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyApp(activity: ListActivity) {
    val listViewModel: ListViewModel = hiltViewModel()
    val state by listViewModel.state.collectAsState()

    val snackbarHostState = SnackbarHostState()

    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { data ->
                    Snackbar(
                        data,
                        modifier = Modifier.background(Color.Red)
                    )
                }
            )
        },
        topBar = {
            TopAppBar(
                title = { Text(Constantes.LISTA_DE_PERSONAS) },
                navigationIcon = {
                    IconButton(onClick = { activity.finish() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = Constantes.IR_HACIA_ARRIBA)
                    }
                },
            )
        },
        content = {
            Content(it, listViewModel, state)

            state.error?.let {
                scope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(it)
                    listViewModel.handleEvent(ListEvent.LimpiarError())
                }
            }
        }
    )
}

@Composable
fun Content(paddingValues: PaddingValues, listViewModel: ListViewModel, state: ListState) {

    listViewModel.handleEvent(ListEvent.GetAllPersons)

    val personas: List<Persona> = state.listaPersona

    LazyColumn(contentPadding = paddingValues) {
        items(
            items = personas,
            itemContent = {
                PersonaListItem(persona = it)
            }
        )
    }
}

@Composable
fun PersonaListItem(persona: Persona) {
    Card(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 8.dp)
            .fillMaxWidth(),
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
                Text(text = persona.nombre)
                Text(text = persona.apellido)
            }
        }
    }
}