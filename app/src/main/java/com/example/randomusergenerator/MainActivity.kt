package com.example.randomusergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.ui.theme.RandomUserGeneratorTheme
import com.example.randomusergenerator.user.UserDetailsScreen
import com.example.randomusergenerator.user.UserScreen
import com.example.randomusergenerator.user.viewmodel.UserViewModel
import com.example.randomusergenerator.utils.DETAIL_ARG_KEY
import com.example.randomusergenerator.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomUserGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }

    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        val userViewModel: UserViewModel = hiltViewModel()
        val userViewState by userViewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = Screen.UserScreen.route,
        ) {
            composable(route = Screen.UserScreen.route) {
                UserScreen(navController = navController, userViewModel = userViewModel)
            }
            composable(
                route = Screen.UserDetailsScreen.route,
                arguments = listOf(navArgument(DETAIL_ARG_KEY) { type = NavType.IntType })
            ) { backStackEntry ->
                val user = backStackEntry.arguments?.getInt(DETAIL_ARG_KEY)
                val selectedItem: UserData? = userViewState.users?.find { it.id == user }
                if (selectedItem != null) {
                    UserDetailsScreen(selectedItem)
                }
            }
        }
    }
}
