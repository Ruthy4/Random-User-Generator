package com.example.randomusergenerator.utils

object Constants {
    const val BASE_URL = "https://randomuser.me/"
    const val DATABASE_NAME = "user_db"
    const val NUM_OF_USERS = 50
    // Network Messages
    const val SOCKET_TIME_OUT_EXCEPTION = "Request timed out while trying to connect. Please ensure you have a strong signal and try again."
    const val UNKNOWN_NETWORK_EXCEPTION = "An unexpected error has occurred. Please check your network connection and try again."
    const val CONNECT_EXCEPTION = "Could not connect to the server. Please check your internet connection and try again."
    const val UNKNOWN_HOST_EXCEPTION = "Couldn't connect to the server at the moment. Please try again in a few minutes."
    const val SSL_EXCEPTION = "Software caused connection abort. Please check your internet connection and try again."
}
