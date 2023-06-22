package com.example.randomusergenerator.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DateOfBirth(
    val age: Int? = 0,
    val date: String? = null,
)
