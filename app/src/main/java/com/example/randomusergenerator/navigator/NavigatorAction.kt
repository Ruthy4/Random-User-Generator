package com.example.randomusergenerator.navigator

import androidx.navigation.NavHostController

interface NavigatorAction {
    fun executeOn(navController: NavHostController)
}
