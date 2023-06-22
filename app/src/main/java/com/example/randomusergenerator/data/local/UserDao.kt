package com.example.randomusergenerator.data.local

import androidx.room.*
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomusergenerator.data.remote.dto.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<User>?)

    @Query("SELECT * FROM user")
    suspend fun getUser(): List<User>

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}
