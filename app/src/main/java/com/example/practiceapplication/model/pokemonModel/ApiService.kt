package com.example.practiceapplication.model.pokemonModel

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    //Crear el método para acceder al API
    @GET("pokemon/{id}")
   suspend fun getPokemonsByName(@Path("id") id:Int): Response<PokemonModel>
}