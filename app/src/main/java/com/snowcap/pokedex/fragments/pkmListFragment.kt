package com.snowcap.pokedex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.snowcap.pokedex.R
import kotlinx.android.synthetic.main.fragment_pkm_list.*

class pkmListFragment : Fragment() {
    private val args: pkmListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pkm_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        welcomeText.text = when (args.trainer.gender) {
            "Male" -> "Bienvenido, ${args.trainer.name}"
            else -> "Bienvenida, ${args.trainer.name}"
        }
    }
 }

