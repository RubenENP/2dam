package com.example.composerubenhita.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composerubenhita.R
import com.example.composerubenhita.common.Constantes
import com.example.composerubenhita.ui.list.ListActivity
import com.example.composerubenhita.ui.main.theme.ComposeRubenHitaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRubenHitaTheme {
                val mainViewModel: MainViewModel = hiltViewModel()

                val state by mainViewModel.state.collectAsState()

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
                    content = { paddingValues ->
                        state.error?.let { error ->
                            scope.launch {
                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(error)
                                mainViewModel.handleEvent(MainEvent.LimpiarError())
                            }
                        }

                        if (state.isLoading) {
                            CircularProgressIndicator()
                        } else {
                            All(
                                Modifier.padding(paddingValues),
                                mainViewModel = mainViewModel
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                mainViewModel.handleEvent(
                                    MainEvent.AddPersona(
                                        state.persona.nombre,
                                        state.persona.apellido
                                    )
                                )
                            },
                        ) {
                            Icon(Icons.Filled.Add, Constantes.ADD_PERSON)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun All(
    modifier: Modifier,
    mainViewModel: MainViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 100.dp, 0.dp, 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {

            mainViewModel.handleEvent(MainEvent.SetNombre(textFieldView(
                texto = Constantes.NOMBRE,
                Modifier
                    .align(Alignment.CenterHorizontally)
            )))

            mainViewModel.handleEvent(MainEvent.SetApellido(textFieldView(
                texto = Constantes.APELLIDO,
                Modifier
                    .align(Alignment.CenterHorizontally)
            )))

            DisplayImage(
                Modifier
                    .padding(16.dp)
                    .width(200.dp)
                    .height(200.dp)
                    .align(Alignment.CenterHorizontally)
            )

            val mContext = LocalContext.current

            Button(
                onClick = { mContext.startActivity(Intent(mContext, ListActivity::class.java)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = Constantes.VER_TODAS_LAS_PERSONAS)
            }
        }
    }
}

@Composable
fun DisplayImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.darell),
        contentDescription = Constantes.IMAGE,
        modifier = modifier,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldView(texto: String, modifier: Modifier): String {
    val textState = remember {
        mutableStateOf(Constantes.COMILLAS)
    }

    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        label = {
            Text(text = texto)
        },
        modifier = modifier
    )

    return textState.value
}