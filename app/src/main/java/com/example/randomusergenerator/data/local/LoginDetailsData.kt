package com.example.randomusergenerator.data.local

import com.example.randomusergenerator.data.remote.dto.LoginDetails

data class LoginDetailsData(
    val username: String? = null,
    val password: String? = null,
) {
    companion object {
        fun from(value: LoginDetails?): LoginDetailsData {
            return LoginDetailsData(
                username = value?.username,
                password = value?.password
            )
        }
    }
}
