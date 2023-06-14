package com.example.randomusergenerator.data.repository

import androidx.room.withTransaction
import com.example.randomusergenerator.data.local.UserDatabase
import com.example.randomusergenerator.data.local.UserEntity
import com.example.randomusergenerator.data.local.UserGeneratorResponseEntity
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.utils.Resource
import com.example.randomusergenerator.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: UserDatabase
) : UserRepository {
    private val userDao = database.userGeneratorResponseDao()

    override suspend fun getAllUsers(results: Int): Flow<Resource<UserGeneratorResponseEntity>?> =
        flow {
            emit(Resource.Loading(null))

            val localData = userDao.getUser()

            emit(Resource.Success(localData))

            try {
                val remoteData = apiService.getUsers(results)

                database.withTransaction {
                    userDao.deleteUser()
                    userDao.insertUser(remoteData)
                }

                emit(Resource.Success(remoteData))
            } catch (e: IOException) {
                emit(Resource.Error(e, localData))
            } catch (e: HttpException) {
                emit(Resource.Error(e, localData))
            }
        }

//        networkBoundResource(
//            query = {
//                userDao.getUser()
//            },
//            fetch = {
//                apiService.getUsers(results)
//            },
//            saveFetchResult = {
//                database.withTransaction {
//                    userDao.deleteUser()
//                    userDao.insertUser(it)
//                }
//            }
//        )
}


