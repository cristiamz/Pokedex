package com.snowcap.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snowcap.pokedex.R
import com.snowcap.pokedex.models.Pokemon
import com.snowcap.pokedex.viewholders.PokemonItemViewHolder
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class PokemonAdapter : RecyclerView.Adapter<PokemonItemViewHolder>() {

    private val clickListener: PublishSubject<Pokemon> = PublishSubject.create()

    val onItemClicked: Observable<Pokemon> = clickListener.hide()

    var pokedexList: List<Pokemon> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonItemViewHolder, position: Int) {
         holder.bind(pokedexList[position], clickListener)
    }
    override fun getItemCount() = pokedexList.size

}