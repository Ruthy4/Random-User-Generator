package com.example.randomusergenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomusergenerator.presentation.ui.theme.RandomUserGeneratorTheme
import com.example.randomusergenerator.presentation.user.UserViewModel
import com.example.randomusergenerator.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
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
                    UserScreen()
                }
            }
        }
    }
}

@Composable
fun UserScreen(userViewModel: UserViewModel = hiltViewModel()) {
    val userState by userViewModel.userResponse.collectAsState()

    when (val resource = userState) {
        is Resource.Loading -> {
            // Show loading indicator
            CircularProgressIndicator()
        }
        is Resource.Success -> {
            val userData = resource.data
            if (userData != null) {
                // Display the user data
//                UserList(userData.result)
                Timber.d("All Users ${userData.result}")
            } else {
                // Show empty state
                Text("No users available")
            }
        }
        is Resource.Error -> {
            // Show error message
            Text("Error occurred: ${resource.error?.message}")
        }
        else -> Unit
    }

    // Trigger data fetching
    LaunchedEffect(Unit) {
        userViewModel.getAllUsers(results = 10)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RandomUserGeneratorTheme {

    }
}
