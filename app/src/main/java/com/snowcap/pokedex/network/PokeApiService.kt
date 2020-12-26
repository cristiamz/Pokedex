package com.snowcap.pokedex.network

import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.models.Pokemon.PokemonListDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<PokemonListDataResponse>

    @GET("pokemon/{pokemonId}")
    fun getPokemonDetail(@Path("pokemonId") pokemonId: Int): Call<Pokemon>
}