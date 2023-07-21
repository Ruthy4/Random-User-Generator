package com.example.randomusergenerator.navigator

import com.example.randomusergenerator.navigator.actions.GoBackTo
import com.example.randomusergenerator.navigator.actions.NavigateTo
import com.example.randomusergenerator.navigator.actions.NavigateUp
import com.example.randomusergenerator.navigator.actions.PopUpTo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManagerImpl @Inject constructor() : NavigationManager {
    private val _commands = MutableStateFlow<NavigatorAction?>(null)

    override val commands: StateFlow<NavigatorAction?>
        get() = _commands.asStateFlow()

    private var navigationCache = mutableMapOf<String, Any?>()

    override fun <T> getArgument(route: String): T? {
        val argument = navigationCache[route]
        return argument as? T
    }

    override fun navigateTo(destination: String) {
        _commands.value = NavigateTo(destination)
    }

    override fun navigateTo(destination: String, argument: Any) {
        navigationCache[destination] = argument
        _commands.value = NavigateTo(destination)
    }

    override fun navigateUp() {
        _commands.value = NavigateUp()
    }

    override fun popUpTo(destination: String) {
        _commands.value = PopUpTo(destination)
    }

    override fun goBackTo(destination: String) {
        _commands.value = GoBackTo(destination)
    }
}
