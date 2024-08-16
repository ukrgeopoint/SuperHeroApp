package com.example.superheroes

import com.example.superheroes.data.HeroItem
import retrofit2.Retrofit

class Repository(private val apiClient: Retrofit) {
    suspend fun getCurrencyHero(): ArrayList<HeroItem> {
        val apiInterface = apiClient.create(ApiInterface::class.java)
        return apiInterface.getHeroes()
    }
}