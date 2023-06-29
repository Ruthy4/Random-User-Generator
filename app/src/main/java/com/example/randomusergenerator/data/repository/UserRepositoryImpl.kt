package com.example.randomusergenerator.data.repository

import androidx.room.withTransaction
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.data.local.UserDatabase
import com.example.randomusergenerator.data.local.dao.UserDao
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.utils.Resource
import com.example.randomusergenerator.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
//    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {
    override suspend fun getAllUsers(results: Int) : Resource<List<UserData>> {

        val localData = userDao.getUser()
        val localUserResult = UserData.from(localData)

        Resource.Loading(localUserResult)

        val remoteData = safeApiCall {
            apiService.getUsers(results)
        }

        return when (remoteData) {
            is Resource.Success -> {
                userDao.updateUser(remoteData.data?.results)

//                val userResult = UserData.from(remoteData.data?.results ?: emptyList())
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
}

//        return flow {
//
//            emit(Resource.Loading(null))
//
//            val localData = userDao.getUser()
//            val localUserResult = UserData.from(localData)
//
//            emit(Resource.Success(localUserResult))
//
//            val remoteData = safeApiCall {
//                apiService.getUsers(results)
//            }
//
//            when (remoteData) {
//                is Resource.Success -> {
//                    database.withTransaction {
//                        userDao.clearAllUsers()
//                        userDao.insertUser(remoteData.data?.results)
//                    }
//
//                    val userResult = UserData.from(remoteData.data?.results ?: emptyList())
//                    emit(Resource.Success(userResult))
//                }
//
//                is Resource.Error -> {
//                    emit(Resource.Error(remoteData.error, localUserResult))
//                }
//
//                else -> {
//                    emit(Resource.Loading(null))
//                }
//            }


