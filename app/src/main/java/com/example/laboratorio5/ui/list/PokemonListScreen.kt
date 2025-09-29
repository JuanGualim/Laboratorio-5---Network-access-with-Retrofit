package com.example.laboratorio5.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.laboratorio5.network.RetrofitClient
import com.example.laboratorio5.network.PokeResult


@Composable
fun PokemonListScreen(
    onPokemonClick: (String, String) -> Unit
) {
    var pokemonList by remember { mutableStateOf<List<PokeResult>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.apiService.getPokemonList(100)
            pokemonList = response.results
            isLoading = false
        } catch (e: Exception) {
            error = e.message
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $error")
            }
        }
        else -> {
            Column {
                Text(
                    text = "Lista de Pokémon",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFFFFFFFF),
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF2196F3))
                        .padding(horizontal = 16.dp, vertical = 15.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    items(pokemonList) { pokemon ->
                        val id = pokemon.url.trimEnd('/').substringAfterLast('/')
                        PokemonItem(
                            name = pokemon.name,
                            id = id,
                            onClick = { onPokemonClick(id, pokemon.name) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(name: String, id: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"),
                contentDescription = name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewPokemonItem() {
    PokemonItem(
        name = "Bulbasaur",
        id = "1",
        onClick = {}
    )
}



