package com.example.randomusergenerator.data.local

import androidx.room.*

@Dao
interface UserGeneratorResponseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(response: UserGeneratorResponseEntity?)

    @Query("SELECT * FROM user_response")
    suspend fun getUser(): UserGeneratorResponseEntity?

    @Query("DELETE FROM user_response")
    suspend fun deleteUser()
}
