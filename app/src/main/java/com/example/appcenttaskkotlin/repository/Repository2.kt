package com.example.appcenttaskkotlin.repository

import com.example.appcenttaskkotlin.api.RetrofitInstance2
import com.example.appcenttaskkotlin.appcentModel.Post1
import retrofit2.Response

class Repository2 {
    suspend fun getPost1():Response<Post1>
    {
        return RetrofitInstance2.api1.getPost1()
    }

}