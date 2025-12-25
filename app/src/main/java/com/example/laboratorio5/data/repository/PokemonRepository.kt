package com.example.laboratorio5.data.repository

import com.example.laboratorio5.data.remote.RetrofitClient
import com.example.laboratorio5.network.Pokemon

class PokemonRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getPokemonList(limit: Int): List<Pokemon>{
        val response = apiService.getPokemonList(limit)
        return response.results.map { pokeResult ->
            val id = pokeResult.url.trimEnd('/').substringAfterLast('/')
            Pokemon(
                name = pokeResult.name,
                id = id
            )
        }
    }
}