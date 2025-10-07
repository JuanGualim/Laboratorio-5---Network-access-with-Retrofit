package com.example.laboratorio5.ui.detail

import androidx.lifecycle.ViewModel
import com.example.laboratorio5.network.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PokemonDetailUiState(
    val pokemon: Pokemon? = null
)

class PokemonDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailUiState())
    val uiState: StateFlow<PokemonDetailUiState> = _uiState.asStateFlow()

    fun setPokemon(id: String, name: String) {
        _uiState.value = PokemonDetailUiState(
            pokemon = Pokemon(name = name, id = id)
        )
    }
}