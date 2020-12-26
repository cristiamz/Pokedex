package com.snowcap.pokedex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.network.RetrofitProvider
import com.snowcap.pokedex.models.Pokemon.PokemonListDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel : ViewModel() {
    private val retrofitProvider = RetrofitProvider()
    private val offset: Int = 0
    private val limit: Int = 20

    private val pokemonListResponse: MutableLiveData<List<Pokemon>> = MutableLiveData()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getPokemonListResponse() : LiveData<List<Pokemon>> {
        return pokemonListResponse
    }

    fun getIsLoading() : LiveData<Boolean> {
        return isMakingRequest
    }

    fun getIsError() : LiveData<Boolean> {
        return isError
    }

    fun getPokemonList() {
        isMakingRequest.postValue(true)
        retrofitProvider.getPokeApiService().getPokemonList(offset, limit)
            .enqueue(object :
                Callback<PokemonListDataResponse> {
                override fun onResponse(
                    call: Call<PokemonListDataResponse>,
                    response: Response<PokemonListDataResponse>
                ) {
                    isMakingRequest.postValue(false)
                    if (response.isSuccessful) {
                        response.body()?.let { unwrappedResponse ->
                            pokemonListResponse.postValue(unwrappedResponse.results)
                        }
                    } else {
                        isError.postValue(true)
                    }
                }

                override fun onFailure(call: Call<PokemonListDataResponse>, t: Throwable) {
                    isMakingRequest.postValue(false)
                    isError.postValue(true)
                }
            })
    }

    }
