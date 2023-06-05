package com.example.randomusergenerator.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/api/")
    fun getUsers(@Query("results") results: String)
}
