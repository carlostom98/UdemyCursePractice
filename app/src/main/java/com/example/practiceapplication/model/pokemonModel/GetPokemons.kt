package com.example.practiceapplication.model.pokemonModel

class GetPokemons(var id:Int) {

    private val services=Service()
    suspend operator fun invoke() = services.getPokemons(id)
}