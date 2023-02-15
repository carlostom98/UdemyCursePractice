package com.example.practiceapplication.model.pokemonModel

import com.google.gson.annotations.SerializedName

data class NameAndImage(@SerializedName("name") val pokemonName:String, val url:String)
