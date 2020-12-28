package com.snowcap.pokedex.network

import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.models.Pokemon.PokemonListDataResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeApiService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Observable<PokemonListDataResponse>

    @GET("pokemon/{pokemonName}")
    fun getPokemonDetail(@Path("pokemonName") pokemonId: String): Observable<Pokemon>
}