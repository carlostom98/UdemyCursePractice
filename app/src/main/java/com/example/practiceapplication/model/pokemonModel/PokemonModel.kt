package com.example.practiceapplication.model.pokemonModel

import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("name") val name: String,
    @SerializedName("weight") val peso: Int,
    @SerializedName("sprites") val image: Images
)