package com.snowcap.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snowcap.pokedex.R
import com.snowcap.pokedex.models.Pokemon.Pokemon
import com.snowcap.pokedex.viewholders.PokemonViewHolder

class PokemonListAdapter(val clickListener:(Pokemon) -> Unit)  : RecyclerView.Adapter<PokemonViewHolder>() {

//    private val clickListener: PublishSubject<Pokemon> = PublishSubject.create()
//    val onItemClicked: Observable<Pokemon> = clickListener.hide()

    var pokedexList: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
         holder.bind(pokedexList[position], clickListener)
    }
    override fun getItemCount() = pokedexList.size

}