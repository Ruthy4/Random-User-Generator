package com.example.randomusergenerator.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Info(
    val seed: String? = null,
    val results: Int? = 0,
    val page: Int? = 0,
    val version: String? = null,
)
