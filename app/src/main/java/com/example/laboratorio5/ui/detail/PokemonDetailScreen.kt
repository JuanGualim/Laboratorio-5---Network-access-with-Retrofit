package com.example.laboratorio5.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonDetailScreen(
    id: String,
    name: String,
    onBackClick: () -> Unit = { },
    viewModel: PokemonDetailViewModel = viewModel()
) {
    LaunchedEffect(id, name) {
        viewModel.setPokemon(id, name)
    }

    val uiState by viewModel.uiState.collectAsState()
    val pokemon = uiState.pokemon

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2196F3)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFFFFFFFF)
                )
            }
            Text(
                text = "Detalle de Pokémon",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFFFFFFFF),
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF2196F3))
                    .padding(horizontal = 16.dp, vertical = 15.dp)
            )
        }

        if (pokemon != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.headlineMedium
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            PokemonImage(pokemon.imageUrlFront, "Frente")
                            PokemonImage(pokemon.imageUrlBack, "Espalda")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            PokemonImage(pokemon.imageUrlShinyFront, "Frente Shiny")
                            PokemonImage(pokemon.imageUrlShinyBack, "Espalda Shiny")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonImage(url: String, contentDescription: String) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = contentDescription,
        modifier = Modifier.size(120.dp),
        contentScale = ContentScale.Crop
    )
}