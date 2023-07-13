package com.example.randomusergenerator.data.local

data class UserViewState(
    val isLoading: Boolean = false,
    val users: List<UserData>? = emptyList(),
    val errorMessage: String? = null
)
