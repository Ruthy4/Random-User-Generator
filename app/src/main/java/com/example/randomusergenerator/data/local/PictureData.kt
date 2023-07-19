package com.example.randomusergenerator.data.local

import com.example.randomusergenerator.data.remote.dto.Picture

data class PictureData(
    val thumbnail: String? = null
) {
    companion object {
        fun from(value: Picture?): PictureData {
            return PictureData(
                thumbnail = value?.thumbnail
            )
        }
    }
}
