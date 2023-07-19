package com.example.randomusergenerator.data.local

import com.example.randomusergenerator.data.remote.dto.Name

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
