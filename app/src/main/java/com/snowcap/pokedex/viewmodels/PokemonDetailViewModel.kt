package com.snowcap.pokedex.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.snowcap.pokedex.network.RetrofitProvider
import com.snowcap.pokedex.network.models.PokemonDetail
import com.snowcap.pokedex.network.models.PokemonItem
import com.snowcap.pokedex.network.models.PokemonListDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonDetailViewModel : ViewModel() {
    private val retrofitProvider = RetrofitProvider()

    private val pokemonDetail: MutableLiveData<PokemonDetail> = MutableLiveData()
    private val isMakingRequest: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()

    fun getPokemonListResponse() : LiveData<PokemonDetail> {
        return pokemonDetail
    }

    fun getIsLoading() : LiveData<Boolean> {
        return isMakingRequest
    }

    fun getIsError() : LiveData<Boolean> {
        return isError
    }

    fun getPokemonDetail(pokemonId: Int) {
        isMakingRequest.postValue(true)
        retrofitProvider.getPokeApiService().getPokemonDetail(pokemonId)
            .enqueue(object :
                Callback<PokemonDetail> {
                override fun onResponse(
                    call: Call<PokemonDetail>,
                    response: Response<PokemonDetail>
                ) {
                    isMakingRequest.postValue(false)
                    if (response.isSuccessful) {
                        response.body()?.let { unwrappedResponse ->
                            pokemonDetail.postValue(unwrappedResponse)
                        }
                    } else {
                        isError.postValue(true)
                    }
                }

                override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                    isMakingRequest.postValue(false)
                    isError.postValue(true)
                }
            })
    }

}
