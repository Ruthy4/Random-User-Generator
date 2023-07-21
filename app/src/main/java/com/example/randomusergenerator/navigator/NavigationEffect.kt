package com.example.randomusergenerator.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.randomusergenerator.navigator.viewmodel.NavigationViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationEffect(
    navController: NavHostController,
    viewModel: NavigationViewModel = hiltViewModel()
) {
    LaunchedEffect("navigation") {
        viewModel.navigationManager.commands.onEach {
            it?.executeOn(navController)
        }.launchIn(this)
    }
}
