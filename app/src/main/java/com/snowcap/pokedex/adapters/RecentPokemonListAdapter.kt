package com.snowcap.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.snowcap.pokedex.R
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.viewholders.PokemonViewHolder
import com.snowcap.pokedex.viewholders.RecentPokemonViewHolder
import java.util.*

class RecentPokemonListAdapter(val clickListener:(Pokemon) -> Unit)  :
    RecyclerView.Adapter<RecentPokemonViewHolder>(), Filterable {

    var pokedexList: List<String> = emptyList()
        set(value) {
            field = value
            filteredPokemons = value
            notifyDataSetChanged()
        }

    var favoritePokemon: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var filteredPokemons: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentPokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recent_pokemon_list_item, parent, false)
        return RecentPokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentPokemonViewHolder, position: Int) {
         holder.bind(filteredPokemons[position])
    }

    override fun getItemCount() = filteredPokemons.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                filteredPokemons = pokedexList
                charSequence?.let {
                    if (charSequence.isNotEmpty()) {
                        filteredPokemons = pokedexList.filter { character ->
                            character.toLowerCase(
                                Locale.getDefault()
                            ).contains(charSequence.toString().toLowerCase(Locale.getDefault()))
                        }
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = filteredPokemons
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filteredResults: FilterResults?
            ) {
                filteredPokemons = filteredResults?.values as List<String>
                notifyDataSetChanged()
            }
        }
    }

}