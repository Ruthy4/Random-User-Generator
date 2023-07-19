package com.example.randomusergenerator.utils

const val DETAIL_ARG_KEY = "userId"

sealed class Screen(val route: String) {
    object UserScreen : Screen("user_screen")
    object UserDetailsScreen : Screen("user_details_screen/{$DETAIL_ARG_KEY}")
}
