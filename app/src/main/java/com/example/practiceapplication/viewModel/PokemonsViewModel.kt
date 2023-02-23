package com.example.practiceapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiceapplication.model.pokemonModel.GetPokemons
import com.example.practiceapplication.model.pokemonModel.PokemonModel
import kotlinx.coroutines.launch

class PokemonsViewModel:ViewModel() {
    val whoIsThatPokemon =  MutableLiveData<PokemonModel>()

    fun giveMeAPokemon(id:Int){

        viewModelScope.launch {
            val pokemons=GetPokemons(id).invoke()
            pokemons?.let {
                whoIsThatPokemon.postValue(it)
            }
        }
    }
}