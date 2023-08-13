package com.example.randomusergenerator.data.repository

import com.example.randomusergenerator.data.local.UserData
import com.example.randomusergenerator.data.local.dao.UserDao
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.data.remote.dto.UserResponse
import com.example.randomusergenerator.util.sampleUser
import com.example.randomusergenerator.util.sampleUserData
import com.example.randomusergenerator.util.sampleUserResponse
import com.example.randomusergenerator.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
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

    val captor: ArgumentCaptor<Int> = ArgumentCaptor.forClass(Int::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        serviceUnderTest = UserRepositoryImpl(mockApiService, mockUserDao)
    }

    @Test
    fun `when getUsers is called then return a successful list of users`() = runTest {
        val expectedData = Resource.Success(listOf(sampleUserData))
        val userList = listOf(sampleUser)
        // GIVEN
        whenever(mockUserDao.getUser()).thenReturn(userList)
        whenever(mockApiService.getUsers(numberOfUsers))
            .thenReturn(Response.success(sampleUserResponse))
        whenever(mockUserDao.updateUser(any())).thenReturn(Unit)

        // WHEN
        val resultFlow = serviceUnderTest.getAllUsers(numberOfUsers)
        val result = mutableListOf<Resource<List<UserData>>>()
        resultFlow.collect {
            result.add(it)
        }

        // THEN
        val initialDbResult = mockUserDao.getUser()
        assertTrue(initialDbResult.isNotEmpty())
        assertEquals(1, initialDbResult.size)

        verify(mockApiService).getUsers(numberOfUsers)
        verify(mockApiService).getUsers(captor.capture())

        val capturedParam = captor.value

        mockUserDao.updateUser(userList)
        val actualDbResult = mockUserDao.getUser()
        assertTrue(actualDbResult.isNotEmpty())
        assertEquals(expectedData.data?.first()?.email, actualDbResult.first().email)
        assertEquals(numberOfUsers, capturedParam)
    }

    @Test
    fun `when network call is made and has an error, then return appropriate error response`() =
        runTest {
            val errorText = "There is an error"
            val errorResponse = Response.error<UserResponse>(
                404,
                errorText.toResponseBody(null)
            )
            val userList = listOf(sampleUser)

            // GIVEN
            whenever(mockUserDao.getUser()).thenReturn(userList)
            whenever(mockApiService.getUsers(numberOfUsers))
                .thenReturn(errorResponse)

            // WHEN
            val resultFlow = serviceUnderTest.getAllUsers(numberOfUsers)

            val result = mutableListOf<Resource<List<UserData>>>()
            resultFlow.collect {
                result.add(it)
            }

            // THEN
            verify(mockApiService).getUsers(numberOfUsers)
            verify(mockApiService).getUsers(captor.capture())

            assertTrue(result.size == 2)
            assertTrue(result[0] is Resource.Loading)
            assertTrue(result[1] is Resource.Error)
        }

    @Test
    fun `when searchUser is called then return user from database`() = runTest {
        // GIVEN
        val searchQuery = "John"
        val userList = listOf(sampleUser)
        whenever(mockUserDao.searchDatabase(searchQuery)).thenReturn(flowOf(userList))

        // WHEN
        serviceUnderTest.searchUser(searchQuery)

        // THEN
        verify(mockUserDao).searchDatabase(searchQuery)

        val actualData = mockUserDao.searchDatabase(searchQuery).first()
        val expectedData = listOf(sampleUserData)
        assertEquals(UserData.from(actualData), expectedData)
    }
}
