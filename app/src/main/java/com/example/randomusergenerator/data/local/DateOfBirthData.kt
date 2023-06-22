package com.example.randomusergenerator.data.local

import com.example.randomusergenerator.data.remote.dto.DateOfBirth

data class DateOfBirthData(
    val age: Int? = 0,
    val date: String? = null,
) {
    companion object {
        fun from(value: DateOfBirth?): DateOfBirthData {
            return DateOfBirthData(
                age = value?.age,
                date = value?.date
            )
        }
    }
}
