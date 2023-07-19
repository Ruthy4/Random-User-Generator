package com.example.randomusergenerator.data.local

import com.example.randomusergenerator.data.remote.dto.Street

data class StreetData(
    val number: Int? = 0,
    val name: String? = null,
) {
    companion object {
        fun from(street: Street?): StreetData {
            return StreetData(
                number = street?.number,
                name = street?.name
            )
        }
    }
}
