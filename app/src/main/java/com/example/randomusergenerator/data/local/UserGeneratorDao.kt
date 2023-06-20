package com.example.randomusergenerator.data.local

import androidx.room.*
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserGeneratorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(response: UserResponseEntity?)

    @Query("SELECT * FROM user_response")
    suspend fun getUser(): UserResponseEntity?

    @Query("DELETE FROM user_response")
    suspend fun deleteUser()
}
