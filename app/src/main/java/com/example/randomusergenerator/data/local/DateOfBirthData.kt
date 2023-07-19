package com.example.randomusergenerator.data.local

import android.os.Parcelable
import com.example.randomusergenerator.data.remote.dto.DateOfBirth
import kotlinx.parcelize.Parcelize

@Parcelize
data class DateOfBirthData(
    val age: Int? = 0,
    val date: String? = null,
) : Parcelable {
    companion object {
        fun from(value: DateOfBirth?): DateOfBirthData {
            return DateOfBirthData(
                age = value?.age,
                date = value?.date
            )
        }
    }
}
