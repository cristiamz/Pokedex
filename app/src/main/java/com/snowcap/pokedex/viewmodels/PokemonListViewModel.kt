package com.snowcap.pokedex.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.snowcap.pokedex.db.PokemonDatabase
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.network.RetrofitProvider
import com.snowcap.pokedex.repository.PokemonRepository
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.launch

class PokemonListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PokemonRepository
    private val database: PokemonDatabase = PokemonDatabase.getDatabase(application)

    init {
        repository = PokemonRepository(database.pokemonDao())
    }

    private val retrofitProvider = RetrofitProvider()
    private val offset: Int = 0
    private val limit: Int = 100

    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getIsLoading(): LiveData<Boolean> {
        return isMakingRequest
    }

    fun getIsError(): LiveData<Boolean> {
        return isError
    }

    fun getPokemonList(): Observable<List<Pokemon>> {
        isMakingRequest.postValue(true)
        return retrofitProvider.getPokeApiService().getPokemonList(offset, limit)
            .map { data -> data.results }
//            .switchMap { list ->
//                Observable.fromIterable(list)
//                    //.filter { pokemon -> pokemon.name.contains(pokemonName) }
//                    .toList()
//                    .toObservable()
//            }
            .doOnError { error ->
                isError.postValue(true)
                isMakingRequest.postValue(false)
            }
            .onErrorReturn { emptyList() }
            .flatMapIterable { list -> list }
            .flatMap { item ->
                retrofitProvider.getPokeApiService()
                    .getPokemonDetail(item.name)
                    .map { detailResponse -> detailResponse }
                    .doOnError { error ->
                        isError.postValue(true)
                        isMakingRequest.postValue(false)
                    }
                    .onErrorReturn { error -> throw error }
            }
            .sorted { first, second -> first.id.compareTo(second.id) }
            .toList()
            .toObservable()
            .doOnNext {
                isMakingRequest.postValue(false)
            }
    }

    fun saveFavorite(pokemon: Pokemon, isFavorite: Boolean) {
        viewModelScope.launch {
            val newPokemon = com.snowcap.pokedex.db.Pokemon(
                pokemon.name,
                isFavorite,
                true
            )
            repository.insert(newPokemon)
        }
    }

}
