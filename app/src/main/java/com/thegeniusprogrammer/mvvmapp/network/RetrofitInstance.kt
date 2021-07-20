package com.thegeniusprogrammer.mvvmapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {


        // Instance of Retrofit
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Return API Interface
    val api by lazy {
        retrofit.create(APIsRequest::class.java)
    }
}