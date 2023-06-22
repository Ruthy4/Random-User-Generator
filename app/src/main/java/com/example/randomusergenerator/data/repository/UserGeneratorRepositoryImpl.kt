package com.example.randomusergenerator.data.repository

import androidx.room.withTransaction
import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.data.local.UserDatabase
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: UserDatabase
) : UserRepository {
    private val userDao = database.userDao()

    override suspend fun getAllUsers(results: Int): Flow<Resource<List<UserData>>?> =
        flow {
            emit(Resource.Loading(null))

            val localData = userDao.getUser()
            val localUserResult = UserData.from(localData)

            emit(Resource.Success(localUserResult))

            try {
                val remoteData = apiService.getUsers(results)

                database.withTransaction {
                    userDao.deleteUser()
                    userDao.insertUser(remoteData.results)
                }

                val userResult = UserData.from(remoteData.results ?: emptyList())
                emit(Resource.Success(userResult))
            } catch (e: IOException) {
                emit(Resource.Error(e, localUserResult))
            } catch (e: HttpException) {
                emit(Resource.Error(e, localUserResult))
            }
        }
}
