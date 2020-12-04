package com.snowcap.pokedex.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.snowcap.pokedex.models.Pokemon
import io.reactivex.rxjava3.core.Observer
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class PokemonItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(pokemon: Pokemon, listener: Observer<Pokemon>) {
        itemView.pokemonNameTextView.text = pokemon.name

        Glide.with(itemView.context)
            .load(pokemon.pictureUrl)
            .circleCrop()
            .into(itemView.pokemonImageView)

        //val isFavoriteIcon = if (pokemon.isFavorite) R.drawable.ic_baseline_star_24 else R.drawable.ic_baseline_star_border_24
        //itemView.favoriteImageView.setImageResource(isFavoriteIcon)

        itemView.setOnClickListener {
            listener.onNext(pokemon)
        }

    }
}