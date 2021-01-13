package com.snowcap.pokedex.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Insert //(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonList: List<Pokemon>);

    @Query("SELECT name FROM pokemon Where isFavorite = 1")
    fun getAllFavoritePokemon() : Flow<List<String>>

    @Query("SELECT * from pokemon WHERE name= :name")
    fun getPokemonByName(name: String): Flow<List<Pokemon?>?>

}