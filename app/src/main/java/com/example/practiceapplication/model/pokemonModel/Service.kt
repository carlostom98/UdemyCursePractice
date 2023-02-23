package com.example.practiceapplication.model.pokemonModel

import com.example.practiceapplication.retrofitObject.Retrofit

class Service {
    private val retrofitService= Retrofit.getRetrofit()

    suspend fun getPokemons(id:Int):PokemonModel?{
        return retrofitService.create(ApiService::class.java).getPokemonsByName(id).body()
    }
}