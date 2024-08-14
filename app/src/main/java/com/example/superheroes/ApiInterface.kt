package com.example.superheroes

import com.example.superheroes.data.HeroItem
import retrofit2.http.GET

interface ApiInterface {
    @GET("/superhero-api/api/all.json")
    suspend fun getHeroes(): ArrayList<HeroItem>
}