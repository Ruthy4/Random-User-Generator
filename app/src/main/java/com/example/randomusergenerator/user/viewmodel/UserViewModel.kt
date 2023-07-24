package com.example.randomusergenerator.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.data.local.UserViewState
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.navigator.NavigationManager
import com.example.randomusergenerator.utils.Constants.NUM_OF_USERS
import com.example.randomusergenerator.utils.Resource
import com.example.randomusergenerator.view.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private var _uiState = MutableStateFlow(UserViewState())
    val uiState: StateFlow<UserViewState> get() = _uiState

    init {
        getAllUsers()
    }
    private fun getAllUsers() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val response = userRepository.getAllUsers(NUM_OF_USERS)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false, users = response.data) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = response.error) }
                }
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun navigateToUserDetails(userData: UserData) {
        navigationManager.navigateTo(Screen.USER_DETAILS_SCREEN.name, userData)
    }

    fun navigateBack() {
        navigationManager.navigateUp()
    }
}
