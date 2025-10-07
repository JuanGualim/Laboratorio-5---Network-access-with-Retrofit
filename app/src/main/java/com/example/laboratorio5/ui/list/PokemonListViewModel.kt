package com.example.laboratorio5.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio5.data.repository.PokemonRepository
import com.example.laboratorio5.network.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class PokemonListUiState {
    object Loading : PokemonListUiState()
    data class Success(val pokemonList: List<Pokemon>) : PokemonListUiState()
    data class Error(val message: String) : PokemonListUiState()
}

class PokemonListViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListUiState>(PokemonListUiState.Loading)
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    init {
        loadPokemonList()
    }

    fun loadPokemonList() {
        viewModelScope.launch {
            _uiState.value = PokemonListUiState.Loading
            try {
                val pokemonList = repository.getPokemonList(100)
                _uiState.value = PokemonListUiState.Success(pokemonList)
            } catch (e: Exception) {
                _uiState.value = PokemonListUiState.Error(
                    e.message ?: "Error desconocido al cargar los pokémones"
                )
            }
        }
    }

    fun retry() {
        loadPokemonList()
    }
}