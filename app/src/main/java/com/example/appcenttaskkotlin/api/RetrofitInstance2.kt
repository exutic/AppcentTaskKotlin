package com.example.appcenttaskkotlin.api

import com.example.appcenttaskkotlin.utils.Constants2.Companion.Base_URL2
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance2 {
    private val retrofit1 by lazy {
        Retrofit.Builder()
            .baseUrl(Base_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api1 : SimpleApi2 by lazy {
        retrofit1.create(SimpleApi2::class.java)
    }
}