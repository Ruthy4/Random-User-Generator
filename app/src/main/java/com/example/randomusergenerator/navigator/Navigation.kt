package com.example.randomusergenerator.navigator

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.randomusergenerator.navigator.viewmodel.NavigationViewModel

fun <T> NavGraphBuilder.composable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (argument: T?, NavBackStackEntry) -> Unit
) {
    composable(route, arguments, deepLinks) {
        val viewModel: NavigationViewModel = hiltViewModel()
        val argument = viewModel.navigationManager.getArgument<T>(route)
        content(argument, it)
    }
}
