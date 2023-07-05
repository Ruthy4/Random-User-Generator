package com.example.randomusergenerator.user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private var _userResponse = MutableStateFlow<Resource<List<UserData>>?>(null)
    val userResponse: StateFlow<Resource<List<UserData>>?> get() = _userResponse

    init {
        getAllUsers(10)
    }

    fun getAllUsers(results: Int) {
        viewModelScope.launch {
            _userResponse.value = Resource.Loading()
            try {
                val response = userRepository.getAllUsers(results)
                _userResponse.value = response
            } catch (e: Exception) {
                _userResponse.value = Resource.Error(e.message ?: "An error occurred")
            }
        }
    }
}