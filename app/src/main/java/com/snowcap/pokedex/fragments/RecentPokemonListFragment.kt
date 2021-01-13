package com.snowcap.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.snowcap.pokedex.R
import com.snowcap.pokedex.adapters.PokemonListAdapter
import com.snowcap.pokedex.adapters.RecentPokemonListAdapter
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.viewmodels.RecentPokemonListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pkm_list.*
import java.util.concurrent.TimeUnit

class RecentPokemonListFragment : Fragment() {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val recentListViewModel: RecentPokemonListViewModel by viewModels()


    private val adapter = RecentPokemonListAdapter { pokemon ->

        val newPokemon : Pokemon = pokemon
        val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment (newPokemon)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        recentListViewModel.getRecentPokemonList()
        return inflater.inflate(R.layout.fragment_recent_pokemon_list, container, false)
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonRecyclerView.adapter = adapter
        pokemonRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        recentListViewModel.getFavoritePokemon().observe(viewLifecycleOwner) { favoriteList ->
            adapter.favoritePokemon = favoriteList
        }

        recentListViewModel.getRecentPokemon().observe(viewLifecycleOwner) { recentList ->
            adapter.pokedexList = recentList
            pokemonRecyclerView.visibility =
                    if (recentList.isEmpty()) View.GONE else View.VISIBLE
                emptyTextView.visibility =
                    if (recentList.isEmpty()) View.VISIBLE else View.GONE
        }

//        recentListViewModel.getRecentPokemonList()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnError { error ->
//                Log.d("error", error.toString())
//            }
//            .onErrorReturn { emptyList() }
//            .subscribe { pokemonList ->
//                adapter.pokedexList = pokemonList
//                pokemonRecyclerView.visibility =
//                    if (pokemonList.isEmpty()) View.GONE else View.VISIBLE
//                emptyTextView.visibility =
//                    if (pokemonList.isEmpty()) View.VISIBLE else View.GONE
//            }

        recentListViewModel.getIsLoading().observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        recentListViewModel.getIsError().observe(viewLifecycleOwner) { isError ->
            Snackbar.make(mainLayout, R.string.error_text, Snackbar.LENGTH_LONG).show()
        }

        disposables.add(SearchTextInputEditText.textChanges()
            .skipInitialValue()
            .debounce(300, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                adapter.filter.filter(it)
            })

    }
}