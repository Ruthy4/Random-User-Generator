package com.example.randomusergenerator.navigator.actions

import androidx.navigation.NavHostController
import com.example.randomusergenerator.navigator.NavigatorAction

class GoBackTo(private val destination: String) : NavigatorAction {
    override fun executeOn(navController: NavHostController) {
        navController.popBackStack(destination, false)
    }
}
