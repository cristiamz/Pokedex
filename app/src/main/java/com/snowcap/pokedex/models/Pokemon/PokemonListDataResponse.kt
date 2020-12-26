package com.snowcap.pokedex.models.Pokemon

import com.snowcap.pokedex.models.Pokemon.Pokemon

data class PokemonListDataResponse(val count: Int, val next: String, val previous: String, val results: List<Pokemon>)