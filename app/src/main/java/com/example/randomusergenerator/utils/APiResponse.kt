package com.example.randomusergenerator.utils

data class ApiResponse<T>(var code: Int = 0, var data: T?, var message: String? = "")