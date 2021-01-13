package com.snowcap.pokedex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.snowcap.pokedex.R
import kotlinx.android.synthetic.main.fragment_pkm_list.*
import kotlinx.android.synthetic.main.fragment_top_menu.*

class TopMenuFragment : Fragment() {

//    private val args: TopMenuFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        welcomeText.text = when (args.Trainer.gender) {
//            "Male" -> "Bienvenido, ${args.Trainer.name}"
//            else -> "Bienvenida, ${args.Trainer.name}"
//        }

        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment2) as NavHostFragment
        bottomNavigationView.setupWithNavController(navHostFragment.navController)
    }
}