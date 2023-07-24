package com.example.randomusergenerator.view

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.navigator.composable
import com.example.randomusergenerator.user.UserScreen
import com.example.randomusergenerator.view.Screen.USER_DETAILS_SCREEN
import com.example.randomusergenerator.view.userdetails.UserDetailsScreen
import javax.inject.Inject

class UserFeatureImpl @Inject constructor() : FeatureApi {
    override fun createGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        with(navGraphBuilder) {
            composable(route = Screen.USER_SCREEN.name) {
                UserScreen()
            }
            composable<UserData>(route = USER_DETAILS_SCREEN.name) { userData, _ ->
                userData?.let {
                    UserDetailsScreen(it)
                }
            }
        }
    }
}
