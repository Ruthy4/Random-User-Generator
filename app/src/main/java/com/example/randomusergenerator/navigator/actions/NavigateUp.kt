package com.example.randomusergenerator.navigator.actions

import androidx.navigation.NavHostController
import com.example.randomusergenerator.navigator.NavigatorAction

class NavigateUp : NavigatorAction {
    override fun executeOn(navController: NavHostController) {
        navController.navigateUp()
    }
}
