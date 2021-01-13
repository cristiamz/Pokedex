package com.snowcap.pokedex.repository

import com.snowcap.pokedex.db.Pokemon
import com.snowcap.pokedex.db.PokemonDAO
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDAO: PokemonDAO) {

    suspend fun insert(pokemon: Pokemon) {
        pokemonDAO.insert(pokemon)
    }

    suspend fun insertAll(pokemonList: List<Pokemon>) {
        pokemonDAO.insertAll(pokemonList)
    }

    suspend fun getPokemonByName(pokemonName: String): Flow<List<Pokemon?>?> {
        return pokemonDAO.getPokemonByName(pokemonName)
    }

    val favoritePokemon: Flow<List<String>> = pokemonDAO.getAllFavoritePokemon()

    val recentPokemon: Flow<List<String>> = pokemonDAO.getAllRecentPokemon()

}