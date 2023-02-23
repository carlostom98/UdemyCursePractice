package com.example.practiceapplication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.example.practiceapplication.databinding.FragmentSecondBinding
import com.example.practiceapplication.model.fragmentsText.FragmentTwoText
import com.example.practiceapplication.model.pokemonModel.ApiService
import com.example.practiceapplication.view.MainActivity
import com.example.practiceapplication.viewModel.PokemonsViewModel
import kotlinx.coroutines.*
import java.lang.System.load

class SecondFragment() : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: MainActivity
    private val args: com.example.practiceapplication.view.fragments.SecondFragmentArgs by navArgs()
    private val pokemonViewModel: PokemonsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.activity = getActivity() as MainActivity
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.searchPokemon.setOnQueryTextListener(this)

        pokemonViewModel.whoIsThatPokemon.observe(activity) {
            with(binding) {
                pokemonName.text = it.name
                pokemonWeight.text = it.peso.toString()
                Glide.with(this@SecondFragment)
                    .load(it.image.imagePokemon)
                    .into(pokemonImage)
            }
        }

        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            pokemonViewModel.giveMeAPokemon(query.toInt())
        } else {
            Toast.makeText(activity, "This pokemon doesn't exist", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}