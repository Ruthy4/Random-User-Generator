package com.example.randomusergenerator.navigator

import kotlinx.coroutines.flow.StateFlow

interface NavigationManager {
    val commands: StateFlow<NavigatorAction?>
    fun <T> getArgument(route: String): T?
    fun navigateTo(destination: String)
    fun navigateTo(destination: String, argument: Any)
    fun navigateUp()
    fun popUpTo(destination: String)
    fun goBackTo(destination: String)
}
