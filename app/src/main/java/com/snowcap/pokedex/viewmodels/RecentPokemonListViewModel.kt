package com.snowcap.pokedex.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.snowcap.pokedex.db.PokemonDatabase
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.network.RetrofitProvider
import com.snowcap.pokedex.repository.PokemonRepository
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.launch

class RecentPokemonListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PokemonRepository
    private val database: PokemonDatabase = PokemonDatabase.getDatabase(application)

    init {
        repository = PokemonRepository(database.pokemonDao())
    }

    private val retrofitProvider = RetrofitProvider()
    private val offset: Int = 0
    private val limit: Int = 20

    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getIsLoading(): LiveData<Boolean> {
        return isMakingRequest
    }

    fun getIsError(): LiveData<Boolean> {
        return isError
    }

    fun getFavoritePokemon(): LiveData<List<String>> = repository.favoritePokemon.asLiveData()

    fun getRecentPokemon(): LiveData<List<String>> = repository.recentPokemon.asLiveData()


}

//    fun getRecentPokemonList(): Observable<MutableList<Pokemon>> {
//        isMakingRequest.postValue(true)
//        return getRecentPokemon()
//            .map { data -> Observable.fromIterable(data)}
//                .flatMapIterable { list -> list }
//                .flatMap { item ->
//                    retrofitProvider.getPokeApiService()
//                        .getPokemonDetail(item)
//                        .map { detailResponse -> detailResponse }
//                        .doOnError { error ->
//                            Log.d("getPokemonList", error.toString())
//                            isError.postValue(true)
//                            isMakingRequest.postValue(false)
//                        }
//                        .onErrorReturn { error ->
//                            Log.d("getPokemonList", error.toString())
//                            throw error
//                        }
//                }
//            }
//            .toList()
//            .toObservable()

//            .flatMapIterable { list -> list }
//            .flatMap { item ->
//                retrofitProvider.getPokeApiService()
//                    .getPokemonDetail(item)
//                    .map { detailResponse -> detailResponse }
//                    .doOnError { error ->
//                        Log.d("getPokemonList", error.toString())
//                        isError.postValue(true)
//                        isMakingRequest.postValue(false)
//                    }
//                    .onErrorReturn { error ->
//                        Log.d("getPokemonList", error.toString())
//                        throw error
//                    }
//            }
//            .sorted { first, second -> first.id.compareTo(second.id) }
//            .toList()
//            .toObservable()
//            .doOnNext {
//                isMakingRequest.postValue(false)
//            }


//    fun getRecentPokemonList(): Observable<List<Pokemon>> {
//        isMakingRequest.postValue(true)
//        return repository.recentPokemon.asLiveData().map { data -> data.listIterator() }
//            //.map { data -> data }
////            .toList()
//            //.toObservable()
//            //.flatMapIterable { list -> list }
//            .flatMap { item ->
//                retrofitProvider.getPokeApiService()
//                    .getPokemonDetail(item)
//                    .map { detailResponse -> detailResponse }
//                    .doOnError { error ->
//                        Log.d("getPokemonList", error.toString())
//                        isError.postValue(true)
//                        isMakingRequest.postValue(false)
//                    }
//                    .onErrorReturn { error ->
//                        Log.d("getPokemonList", error.toString())
//                        throw error
//                    }
//            }
//            .sorted { first, second -> first.id.compareTo(second.id) }
//            .toList()
//            .toObservable()
//            .doOnNext {
//                isMakingRequest.postValue(false)
//            }
//    }


