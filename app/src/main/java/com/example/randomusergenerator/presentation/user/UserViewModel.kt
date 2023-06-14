package com.example.randomusergenerator.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomusergenerator.data.local.UserEntity
import com.example.randomusergenerator.data.local.UserGeneratorResponseEntity
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    private var _userResponse = MutableStateFlow<Resource<UserGeneratorResponseEntity>?>(null)
    val userResponse: StateFlow<Resource<UserGeneratorResponseEntity>?> get() = _userResponse

    fun getAllUsers(results: Int) {
        viewModelScope.launch {
            userRepository.getAllUsers(results).collect {
                _userResponse.emit(it)
            }
        }
    }
}