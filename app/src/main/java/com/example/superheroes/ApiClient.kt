package com.example.superheroes

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val client: Retrofit = Retrofit.Builder()
        .baseUrl("https://akabab.github.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}