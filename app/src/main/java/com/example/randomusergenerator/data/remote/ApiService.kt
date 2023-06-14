package com.example.randomusergenerator.data.remote

import com.example.randomusergenerator.data.local.UserGeneratorResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/")
    suspend fun getUsers(@Query("results") results: Int): UserGeneratorResponseEntity
}
