package com.example.randomusergenerator.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Picture(
    val thumbnail: String? = null
)
