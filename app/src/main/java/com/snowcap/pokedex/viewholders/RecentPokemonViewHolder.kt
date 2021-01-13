package com.snowcap.pokedex.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.snowcap.pokedex.models.Pokemon.Pokemon
import kotlinx.android.synthetic.main.recent_pokemon_list_item.view.*

class RecentPokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: String) {
        itemView.pokemonNameTextView.text = pokemon.capitalize()
    }
}