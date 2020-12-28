package com.snowcap.pokedex.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.network.RetrofitProvider
import io.reactivex.rxjava3.core.Observable

class PokemonListViewModel : ViewModel() {
    private val retrofitProvider = RetrofitProvider()
    private val offset: Int = 200
    private val limit: Int = 50

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
            .map { data ->
                data.results
            }
//            .switchMap { list ->
//                Observable.fromIterable(list)
//                    //.filter { pokemon -> pokemon.name.contains(pokemonName) }
//                    .toList()
//                    .toObservable()
//            }
            .doOnError {error ->
                Log.d("error", error.toString())
                isError.postValue(true)
                isMakingRequest.postValue(false)
            }
            .onErrorReturn { emptyList<Pokemon>() }
            .flatMapIterable { list -> list }
            .flatMap { item ->
                retrofitProvider.getPokeApiService().getPokemonDetail(item.name)
                    .map { detailResponse -> detailResponse }
                    .doOnError { error ->
                        Log.d("error", error.toString())
                        isError.postValue(true)
                        isMakingRequest.postValue(false)
                    }
                    .onErrorReturn { error -> throw error }
            }
            .sorted{first, second -> first.id.compareTo(second.id)}
            .toList()
            .toObservable()
            .doOnNext {
                isMakingRequest.postValue(false)
            }
    }

}
