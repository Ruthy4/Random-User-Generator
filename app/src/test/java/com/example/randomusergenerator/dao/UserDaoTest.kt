package com.example.randomusergenerator.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.randomusergenerator.data.remote.dto.User
import com.example.randomusergenerator.util.BaseDaoTest
import com.example.randomusergenerator.util.TestUtil.parseJsonFileToObject
import com.squareup.moshi.Types
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class UserDaoTest : BaseDaoTest() {
    @Test
    fun `verify that data insertion is successful and returns correct size`() = runTest {
        val type = Types.newParameterizedType(List::class.java, User::class.java)
        val userList = parseJsonFileToObject<List<User>>("sample-user.json", type)

        dao.insertUser(userList)

        val expectedResult = 1
        val actualResult = dao.getUser().size
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `verify that delete is successful`() = runTest {
        val type = Types.newParameterizedType(List::class.java, User::class.java)
        val userList = parseJsonFileToObject<List<User>>("sample-user.json", type)

        dao.insertUser(userList)
        dao.deleteUser(1)

        val expectedResult = 0
        val actualResult = dao.getUser().size
        Assert.assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `verify that user email matches correct email`() = runTest {
        val type = Types.newParameterizedType(List::class.java, User::class.java)
        val userList = parseJsonFileToObject<List<User>>("sample-user.json", type)

        dao.insertUser(userList)

        val expectedResult = userList?.get(0)?.email
        val actualResult = dao.getUser().get(0).email
        Assert.assertEquals(expectedResult, actualResult)
    }
}
