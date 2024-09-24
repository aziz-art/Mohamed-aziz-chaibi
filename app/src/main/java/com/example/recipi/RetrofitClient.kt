package com.example.recipi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton pour gérer l'instance Retrofit
object RetrofitClient {

    const val BASE_URL = "http://10.0.2.2:3000/" // Remplacez par l'URL de votre serveur

    // Instance Retrofit
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Instance de l'interface ApiService
    val api: ApiService = retrofit.create(ApiService::class.java)
}

