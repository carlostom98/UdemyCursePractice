package com.example.practiceapplication.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.practiceapplication.databinding.FragmentSecondBinding
import com.example.practiceapplication.model.fragmentsText.FragmentTwoText
import com.example.practiceapplication.model.pokemonModel.ApiService
import com.example.practiceapplication.view.MainActivity
import com.example.practiceapplication.viewModel.RequestDataViewModel
import kotlinx.coroutines.*

class SecondFragment() : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var activity: MainActivity
    private val args: com.example.practiceapplication.view.fragments.SecondFragmentArgs by navArgs()
    var fragmentTwoText = FragmentTwoText()
    private val requestDataViewModel: RequestDataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.activity = getActivity() as MainActivity
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        // Finalizar todas las corrutinas así una falle
        binding.textSecondFragment = fragmentTwoText
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        searchByName()
    }

    private fun searchByName() {
        lifecycleScope.launch(Dispatchers.IO) {
            val call =
                activity.retrofitO?.create(ApiService::class.java)?.getPokemonsByName()
            val pokemones =call?.body()

                println(pokemones!!.results[1].pokemonName)

        }
    }
}