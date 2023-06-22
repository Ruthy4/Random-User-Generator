package com.example.randomusergenerator.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginDetails(
    val username: String? = null,
    val password: String? = null,
)
