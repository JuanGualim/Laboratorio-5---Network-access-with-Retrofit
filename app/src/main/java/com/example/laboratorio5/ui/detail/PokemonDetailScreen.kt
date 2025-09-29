package com.example.laboratorio5.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonDetailScreen(
    id: String,
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.headlineMedium
        )

        // Cuadro con 4 imágenes
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
                    PokemonImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png")
                    PokemonImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PokemonImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png")
                    PokemonImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png")
                }
            }
        }
    }
}

@Composable
fun PokemonImage(url: String) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        modifier = Modifier.size(120.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewPokemonDetailScreen() {
    PokemonDetailScreen(
        id = "1",
        name = "Bulbasaur"
    )
}

