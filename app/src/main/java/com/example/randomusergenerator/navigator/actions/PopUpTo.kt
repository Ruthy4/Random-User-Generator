package com.example.randomusergenerator.navigator.actions

import androidx.navigation.NavHostController
import com.example.randomusergenerator.navigator.NavigatorAction

class PopUpTo(private val destination: String) : NavigatorAction {
    override fun executeOn(navController: NavHostController) {
        navController.navigate(destination) {
            navController.graph.startDestinationRoute?.let { route ->
                popUpTo(route) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
