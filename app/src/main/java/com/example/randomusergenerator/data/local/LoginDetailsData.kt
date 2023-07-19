package com.example.randomusergenerator.data.local

import android.os.Parcelable
import com.example.randomusergenerator.data.remote.dto.LoginDetails
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginDetailsData(
    val username: String? = null,
    val password: String? = null,
) : Parcelable {
    companion object {
        fun from(value: LoginDetails?): LoginDetailsData {
            return LoginDetailsData(
                username = value?.username,
                password = value?.password
            )
        }
    }
}
