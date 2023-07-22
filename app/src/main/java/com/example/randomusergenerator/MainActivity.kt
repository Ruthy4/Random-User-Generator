package com.example.randomusergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.randomusergenerator.navigator.NavigationEffect
import com.example.randomusergenerator.ui.theme.RandomUserGeneratorTheme
import com.example.randomusergenerator.user.UserScreen
import com.example.randomusergenerator.view.FeatureApi
import com.example.randomusergenerator.view.Screen.USER_SCREEN
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var featureApi: FeatureApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomUserGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(featureApi = featureApi)
                }
            }
        }
    }
}
@Composable
fun MyApp(featureApi: FeatureApi) {
    val navController = rememberNavController()
    NavigationEffect(navController = navController)

    NavHost(
        navController = navController,
        startDestination = USER_SCREEN.name,
    ) {
        featureApi.createGraph(this, navController)
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomUserGeneratorTheme {
        UserScreen()
    }
}
