package com.example.randomusergenerator.data.local

import android.os.Parcelable
import com.example.randomusergenerator.data.remote.dto.Name
import kotlinx.parcelize.Parcelize

data class NameData(
    val title: String? = null,
    val first: String? = null,
    val last: String? = null,
) {
    companion object {
        fun from(name: Name?): NameData {
            return NameData(
                title = name?.title,
                first = name?.first,
                last = name?.last
            )
        }
    }
}
