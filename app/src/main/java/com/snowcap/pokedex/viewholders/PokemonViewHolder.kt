package com.snowcap.pokedex.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snowcap.pokedex.R
import com.snowcap.pokedex.models.Pokemon.Pokemon
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: Pokemon, isFavorite: Boolean, clickListener: (Pokemon) -> Unit) {

        itemView.pokemonNameTextView.text = pokemon.name.capitalize()
        itemView.pokemonSubTextView.text =
            pokemon.types.joinToString(" ") { types -> types.type.name.capitalize() }

        if (pokemon.sprites.front_default == null) {
            itemView.pokemonImageView.setImageResource(R.drawable.ic_baseline_not_interested_24)

        } else {
            Glide.with(itemView.context)
                .load(pokemon.sprites.front_default)
                .circleCrop()
                .into(itemView.pokemonImageView)
        }

        itemView.setOnClickListener {
            clickListener.invoke(pokemon)
        }

        val isFavoriteIcon =
            if (isFavorite) R.drawable.ic_baseline_star_rate_24 else R.drawable.ic_baseline_star_border_24
        itemView.favorite.setImageResource(isFavoriteIcon)

//        itemView.setOnClickListener {
//            clickListener.invoke((pokemon)
//        }
    }
}