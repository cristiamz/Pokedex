package com.snowcap.pokedex.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.animations.DescriptionAnimation
import com.glide.slider.library.slidertypes.BaseSliderView
import com.glide.slider.library.slidertypes.TextSliderView
import com.google.android.material.snackbar.Snackbar
import com.snowcap.pokedex.R
import kotlinx.android.synthetic.main.fragment_pkm_detail.*
import kotlinx.android.synthetic.main.fragment_pkm_detail.view.*
import kotlinx.android.synthetic.main.fragment_pkm_list.*
import kotlinx.android.synthetic.main.pokemon_list_item.view.*


class PokemonDetailFragment : Fragment() {
    private val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pkm_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = args.pokemon

        PokemonName.text = pokemon.name.capitalize()
        base_experienceValue.text = pokemon.base_experience.toString()
        heightValue.text = pokemon.height.toString()
        weightValue.text = pokemon.weight.toString()
        pokemon_typeValue.text =
            pokemon.types.joinToString(" ") { types -> types.type.name.capitalize() }

        if (pokemon.sprites.front_default == null) {
            view.pokemonImage.setImageResource(R.drawable.ic_baseline_not_interested_24)

        } else {
            Glide.with(view.context)
                .load(pokemon.sprites.front_default)
                .circleCrop()
                .into(view.pokemonImage)
        }
    }

//        Glide.with(view.context)
//            .load(pokemon.sprites.front_default)
//            .circleCrop()
//            .into(view.male)
//
//        Glide.with(view.context)
//            .load(pokemon.sprites.front_female)
//            .circleCrop()
//            .into(view.female)
//
//        Glide.with(itemView.context)
//            .load(pokemon.sprites.front_default)
//            .circleCrop()
//            .into(itemView.pokemonImageView)
//
//        Glide.with(itemView.context)
//            .load(pokemon.sprites.front_default)
//            .circleCrop()
//            .into(itemView.pokemonImageView)


//        val requestOptions = RequestOptions()
//        requestOptions.centerCrop()
//            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//            .placeholder(R.drawable.ic_baseline_warning_24)
//            .error(R.drawable.ic_baseline_warning_24)
//
//        if (pokemon.sprites.front_default !== "") {
//            // initialize SliderLayout
//            val front_defaultSliderView: TextSliderView = TextSliderView(context)
//            front_defaultSliderView
//                .image(pokemon.sprites.front_default)
//                .description("Male")
//                .setRequestOption(requestOptions)
//                .setProgressBarVisible(true)
//            //.setOnSliderClickListener(context)
//
//            //add your extra information
//            front_defaultSliderView.bundle(Bundle())
//            front_defaultSliderView.bundle.putString(pokemon.name.capitalize(), "Male")
//            PokemonSlider.addSlider(front_defaultSliderView)
//        }
//
//        if (pokemon.sprites.front_female !== "") {
//            // initialize SliderLayout
//            val front_femaleSliderView: TextSliderView = TextSliderView(context)
//            front_femaleSliderView
//                .image(pokemon.sprites.front_female)
//                .description("Female")
//                .setRequestOption(requestOptions)
//                .setProgressBarVisible(true)
//            //.setOnSliderClickListener(context)
//
//            //add your extra information
//            front_femaleSliderView.bundle(Bundle())
//            front_femaleSliderView.bundle.putString(pokemon.name.capitalize(), "Female")
//            PokemonSlider.addSlider(front_femaleSliderView)
//        }
//
//        if (pokemon.sprites.front_shiny !== "") {
//            // initialize SliderLayout
//            val front_shinySliderView: TextSliderView = TextSliderView(context)
//            front_shinySliderView
//                .image(pokemon.sprites.front_shiny)
//                .description("Shiny Male")
//                .setRequestOption(requestOptions)
//                .setProgressBarVisible(true)
//            //.setOnSliderClickListener(context)
//
//            //add your extra information
//            front_shinySliderView.bundle(Bundle())
//            front_shinySliderView.bundle.putString(pokemon.name.capitalize(), "Shiny Male")
//            PokemonSlider.addSlider(front_shinySliderView)
//        }
//
//        if (pokemon.sprites.front_shiny_female !== "") {
//            // initialize SliderLayout
//            val front_shiny_femaleSliderView: TextSliderView = TextSliderView(context)
//            front_shiny_femaleSliderView
//                .image(pokemon.sprites.front_shiny_female)
//                .description("Shiny Female")
//                .setRequestOption(requestOptions)
//                .setProgressBarVisible(true)
//            //.setOnSliderClickListener(context)
//
//            //add your extra information
//            front_shiny_femaleSliderView.bundle(Bundle())
//            front_shiny_femaleSliderView.bundle.putString(pokemon.name.capitalize(), "Shiny Female")
//            PokemonSlider.addSlider(front_shiny_femaleSliderView)
//        }
//
//        //Global Slider settings
//        PokemonSlider.setPresetTransformer(SliderLayout.Transformer.Accordion)
//        PokemonSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
//        PokemonSlider.setCustomAnimation(DescriptionAnimation())
//        PokemonSlider.setDuration(4000)
//        //PokemonSlider.addOnPageChangeListener(mainLayout)
//        PokemonSlider.stopCyclingWhenTouch(false)
//
//    }
//
//    override fun onStop() {
//        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
//        PokemonSlider.stopAutoCycle()
//        super.onStop()
//    }
//
//    fun onSliderClick(slider: BaseSliderView) {
//        Snackbar.make(mainLayout, slider.bundle.getString("extra") + "", Snackbar.LENGTH_LONG)
//            .show()
//    }
//
//    fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
//
//    fun onPageSelected(position: Int) {}
//
//    fun onPageScrollStateChanged(state: Int) {}


}