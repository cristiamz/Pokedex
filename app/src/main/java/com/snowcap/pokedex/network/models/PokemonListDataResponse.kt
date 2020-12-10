package com.snowcap.pokedex.network.models

data class PokemonListDataResponse(val count: Int, val next: String, val previous: String, val results: List<PokemonItem>)