package com.example.randomusergenerator.data.local.dao

import androidx.room.*
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomusergenerator.data.remote.dto.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    suspend fun updateUser(user: List<User>?) {
        clearAllUsers()
        insertUser(user)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<User>?)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<User>

    @Query("DELETE FROM user WHERE userId = :userId")
    suspend fun deleteUser(userId: Int)

    @Query("DELETE FROM user")
    suspend fun clearAllUsers() // TODO unit tests
    @Query("SELECT * FROM user WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchDatabase(searchQuery: String): Flow<List<User>>
}
