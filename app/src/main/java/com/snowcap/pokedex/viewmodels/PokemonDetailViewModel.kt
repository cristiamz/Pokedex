package com.snowcap.pokedex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.network.RetrofitProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailViewModel : ViewModel() {
    private val retrofitProvider = RetrofitProvider()

    private val pokemonDetail: MutableLiveData<Pokemon> = MutableLiveData()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getPokemonListResponse() : LiveData<Pokemon> {
        return pokemonDetail
    }

    fun getIsLoading() : LiveData<Boolean> {
        return isMakingRequest
    }

    fun getIsError() : LiveData<Boolean> {
        return isError
    }
}
