package com.example.randomusergenerator.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    val title: String? = null,
    val first: String? = null,
    val last: String? = null,
)
