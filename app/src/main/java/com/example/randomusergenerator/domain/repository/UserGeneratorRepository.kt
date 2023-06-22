package com.example.randomusergenerator.domain.repository

import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAllUsers(results: Int): Flow<Resource<List<UserData>>?>
}
