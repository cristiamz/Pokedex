package com.snowcap.pokedex.viewholders

import android.view.View
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snowcap.pokedex.models.Pokemon.Pokemon
import io.reactivex.rxjava3.core.Observer
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: Pokemon, clickListener: (Pokemon)->Unit) {

        itemView.pokemonNameTextView.text = pokemon.name.capitalize()
        //itemView.pokemonSubTextView.text = pokemon.types.first().type.first().name

        //val pkmImage = pokemon.sprite.front_default
        Glide.with(itemView.context)
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/12.png")
            //.load(pkmImage)
            .circleCrop()
            .into(itemView.pokemonImageView)

        itemView.setOnClickListener {
            clickListener.invoke(pokemon)
        }


//        if (pokemon.)
//            itemView.favorite.setImageResource(R.drawable.ic_baseline_star_rate_24)
//        else
//            itemView.favorite.setImageResource(R.drawable.ic_baseline_star_border_24)
//        val isFavoriteIcon = if (pokemon.isFavorite) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_border_24
//        itemView.favoriteImageView.setImageResource(isFavoriteIcon)
//        itemView.setOnClickListener {
//            clickListener.invoke((pokemon)
//        }
    }
}