package com.example.randomusergenerator.data.local

import android.os.Parcelable
import com.example.randomusergenerator.data.remote.dto.Picture
import kotlinx.parcelize.Parcelize

@Parcelize
data class PictureData(
    val thumbnail: String? = null
) : Parcelable {
    companion object {
        fun from(value: Picture?): PictureData {
            return PictureData(
                thumbnail = value?.thumbnail
            )
        }
    }
}
