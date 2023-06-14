package com.example.randomusergenerator.data.local

import androidx.room.*
import com.example.randomusergenerator.data.remote.dto.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun addUser(user: UserGeneratorResponseEntity?)
//
//    @Query("SELECT * FROM user")
//    fun readUser(): Flow<UserGeneratorResponseEntity>
//
//    @Query("DELETE FROM user")
//    suspend fun deleteUser()
}

@Dao
interface UserGeneratorResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(response: UserGeneratorResponseEntity)

    @Query("SELECT * FROM user_generator_response")
    suspend fun getUser():UserGeneratorResponseEntity

    @Query("DELETE FROM user_generator_response")
    suspend fun deleteUser()
}

@Dao
interface InfoDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertInfo(info: InfoEntity)
//
//    @Query("SELECT * FROM info")
//    suspend fun getInfo(): InfoEntity?
}
