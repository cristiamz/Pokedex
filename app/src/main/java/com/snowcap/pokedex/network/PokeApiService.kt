package com.snowcap.pokedex.network

import com.snowcap.pokedex.network.models.PokemonDetail
import com.snowcap.pokedex.network.models.PokemonListDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    fun getPokemonList(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<PokemonListDataResponse>

    @GET("pokemon/{pokemonId}")
    fun getPokemonDetail(@Path("pokemonId") pokemonId: Int): Call<PokemonDetail>
}