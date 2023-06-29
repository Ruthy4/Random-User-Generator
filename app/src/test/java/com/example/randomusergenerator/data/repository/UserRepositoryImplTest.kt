package com.example.randomusergenerator.data.repository

import com.example.randomusergenerator.data.local.UserDatabase
import com.example.randomusergenerator.data.local.dao.UserDao
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.util.sampleUser
import com.example.randomusergenerator.util.sampleUserData
import com.example.randomusergenerator.util.sampleUserResponse
import com.example.randomusergenerator.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class UserRepositoryImplTest {

    @Mock
    lateinit var mockApiService: ApiService

    @Mock
    lateinit var mockUserDao: UserDao

    private lateinit var serviceUnderTest: UserRepositoryImpl

    private val numberOfUsers = 10

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        serviceUnderTest = UserRepositoryImpl(mockApiService, mockUserDao)
    }


    @Test
    fun `when getUsers is called then return a successful list of users`() = runTest {
        val expectedData = Resource.Success(listOf(sampleUserData))
        val userList = listOf(sampleUser)

        //GIVEN
        whenever(mockUserDao.getUser()).thenReturn(userList)
        whenever(mockApiService.getUsers(numberOfUsers))
            .thenReturn(Response.success(sampleUserResponse))
        whenever(mockUserDao.updateUser(any())).thenReturn(Unit)

        //WHEN
        serviceUnderTest.getAllUsers(numberOfUsers)

        //THEN

        val initialDbResult = mockUserDao.getUser()
        assertTrue(initialDbResult.isNotEmpty())
        assertEquals(1, initialDbResult.size)


        verify(mockApiService).getUsers(numberOfUsers)
    //TODO verify that the right value is passed(use argument captor)

        mockUserDao.updateUser(userList)
//        verify(mockUserDao, times(2)).getUser()
        val actualDbResult = mockUserDao.getUser()
        assertTrue(actualDbResult.isNotEmpty())
        assertEquals(expectedData.data?.first()?.email, actualDbResult.first().email )
    }

}




//handle error
//argument capture, verify
//loading
//error