package com.snowcap.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.snowcap.pokedex.R
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.viewholders.PokemonViewHolder
import java.util.*

class PokemonListAdapter(val clickListener:(Pokemon) -> Unit)  :
    RecyclerView.Adapter<PokemonViewHolder>(), Filterable {

//    private val clickListener: PublishSubject<Pokemon> = PublishSubject.create()
//    val onItemClicked: Observable<Pokemon> = clickListener.hide()

    var pokedexList: List<Pokemon> = emptyList()
        set(value) {
            field = value
            filteredPokemons = value
            notifyDataSetChanged()
        }

    private var filteredPokemons: List<Pokemon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
         holder.bind(filteredPokemons[position], clickListener)
    }
    override fun getItemCount() = filteredPokemons.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                filteredPokemons = pokedexList
                charSequence?.let {
                    if (charSequence.isNotEmpty()) {
                        filteredPokemons = pokedexList.filter { character ->
                            character.name.toLowerCase(
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
                filteredPokemons = filteredResults?.values as List<Pokemon>
                notifyDataSetChanged()
            }
        }
    }

}