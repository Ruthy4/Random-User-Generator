package com.example.randomusergenerator.domain.repository

import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.utils.Resource

interface UserRepository {
    suspend fun getAllUsers(results: Int): Resource<List<UserData>>
}
