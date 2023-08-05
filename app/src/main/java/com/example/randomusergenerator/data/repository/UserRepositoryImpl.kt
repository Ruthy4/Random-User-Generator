package com.example.randomusergenerator.data.repository

import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.data.local.dao.UserDao
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.utils.Resource
import com.example.randomusergenerator.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
) : UserRepository {
    override suspend fun getAllUsers(results: Int): Resource<List<UserData>> {

        val localData = userDao.getUser()
        val localUserResult = UserData.from(localData)

        Resource.Loading(localUserResult)

        val remoteData = safeApiCall {
            apiService.getUsers(results)
        }

        return when (remoteData) {
            is Resource.Success -> {
                userDao.updateUser(remoteData.data?.results)

                val userResult = UserData.from(userDao.getUser())
                Resource.Success(userResult)
            }

            is Resource.Error -> {
                Resource.Error(remoteData.error, localUserResult)
            }

            else -> {
                Resource.Loading()
            }
        }
    }

    override suspend fun searchUser(search: String): Flow<List<UserData>> {
        val searchResult = userDao.searchDatabase(search)
        return searchResult.map {
            UserData.from(it)
        }
    }
}
