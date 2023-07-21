package com.example.randomusergenerator.data.local

import com.example.randomusergenerator.util.sampleUserData
import org.junit.Assert.*
import org.junit.Test

class UserDataTest {

    @Test
    fun `when getAddress is called then return a full address`() {
        val expectedResult = "Main Street, New York, NY, USA"
        val actualResult = UserData(location = sampleUserData.location).getAddress

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `when getFullName is called then return a full username`() {
        val expectedResult = "John Doe"
        val actualResult = UserData(name = sampleUserData.name).fullName

        assertEquals(expectedResult, actualResult)
    }
}
