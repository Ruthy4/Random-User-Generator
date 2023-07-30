package com.example.randomusergenerator.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomusergenerator.R
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.data.local.UserViewState
import com.example.randomusergenerator.ui.resources.Dimensions.space_x2
import com.example.randomusergenerator.user.viewmodel.UserViewModel
import com.example.randomusergenerator.view.components.ExpandableSearchToolbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen() {
    val userViewModel: UserViewModel = hiltViewModel()
    val userViewState by userViewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ExpandableSearchToolbar(
                title = stringResource(R.string.app_name),
                onSearchQueryChange = { query ->
                    searchQuery = query
                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.TopStart
            ) {
                UserListScreen(userViewState = userViewState) {
                    userViewModel.navigateToUserDetails(it)
                }
            }
        }
    )
}

@Composable
fun UserListScreen(userViewState: UserViewState, onClick: (UserData) -> Unit) {
    val dialogState = remember { mutableStateOf(false) }

    LaunchedEffect(userViewState.errorMessage) {
        if (userViewState.errorMessage?.isNotBlank() == true) {
            dialogState.value = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = space_x2)
    ) {
        if (userViewState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            userViewState.users?.let { userList ->
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(userList) { user ->
                        UserCard(user) {
                            onClick(it)
                        }
                    }
                }
            }
        }
        if (dialogState.value && userViewState.errorMessage?.isNotBlank() == true) {
            AlertDialog(
                onDismissRequest = { dialogState.value = false },
                title = { Text(stringResource(R.string.error_message)) },
                text = { Text(userViewState.errorMessage.toString()) },
                confirmButton = {
                    Button(onClick = { dialogState.value = false }) {
                        Text(stringResource(R.string.ok))
                    }
                }
            )
        }
    }
}
