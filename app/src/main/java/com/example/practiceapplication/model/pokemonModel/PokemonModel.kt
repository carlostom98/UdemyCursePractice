package com.example.practiceapplication.model.pokemonModel

import com.google.gson.annotations.SerializedName

data class PokemonModel (@SerializedName("results") val results:List<NameAndImage>)