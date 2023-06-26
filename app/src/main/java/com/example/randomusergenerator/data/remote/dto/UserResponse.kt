package com.example.randomusergenerator.data.remote.dto

data class UserResponse(
    val results: List<User>? = emptyList(),
    val info: Info? = null
)
