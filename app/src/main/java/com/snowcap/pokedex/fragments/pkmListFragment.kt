package com.snowcap.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.snowcap.pokedex.R
import com.snowcap.pokedex.adapters.PokemonAdapter
import com.snowcap.pokedex.models.Pokemon
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_pkm_list.*
import java.util.concurrent.TimeUnit

class pkmListFragment : Fragment() {
    private val args: pkmListFragmentArgs by navArgs()
    private val disposables = CompositeDisposable()
    private val adapter = PokemonAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pkm_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposables.clear()

        welcomeText.text = when (args.trainer.gender) {
            "Male" -> "Bienvenido, ${args.trainer.name}"
            else -> "Bienvenida, ${args.trainer.name}"
        }

        pokemonRecyclerView.adapter = adapter
        pokemonRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        pokemonRecyclerView.visibility =
            if (getDummyPokemonList().isEmpty()) View.GONE else View.VISIBLE
        emptyTextView.visibility = if (getDummyPokemonList().isEmpty()) View.VISIBLE else View.GONE
        adapter.pokedexList = getDummyPokemonList()

        disposables.add(adapter.onItemClicked
            .throttleFirst(400, TimeUnit.MILLISECONDS)
            .subscribe {
                //findNavController().navigate(R.id.action_pkmListFragment_to_pkmDetailFragment)
                val action = pkmListFragmentDirections.actionPkmListFragmentToPkmDetailFragment(
                    Pokemon(
                        "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                        "Pikachu"
                    )
                )
                findNavController().navigate(action)
            }
        )
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }

    private fun getDummyPokemonList(): List<Pokemon> {
//        return emptyList()
        return listOf(
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Pikachu"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Garaydos"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Charizard"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Zubat"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Chimeco"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Crobat"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Mr. Mine"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Ho oh!"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Rattata"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Heatran"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Rayquaza"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Glaceon"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Flareon"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Deoxys"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Virizion"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Machamp"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Heracross"
            ),
            Pokemon(
                "https://futureindustrycongress.com/wp-content/uploads/2015/04/speaker-3-v2.jpg",
                "Giratina"
            ),
        )
    }

}


