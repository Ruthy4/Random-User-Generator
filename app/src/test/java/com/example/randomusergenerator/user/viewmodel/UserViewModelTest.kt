package com.example.randomusergenerator.user.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.util.MainCoroutineRule
import com.example.randomusergenerator.util.sampleUserData
import com.example.randomusergenerator.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class UserViewModelTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val coroutineScopeRule = MainCoroutineRule()

    private lateinit var serviceUnderTest: UserViewModel

    @Mock
    lateinit var repository: UserRepository

    private val numberOfUsers = 10

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        serviceUnderTest = UserViewModel(repository)
    }

    @Test
    fun `when get user is called then return users`() = runTest {
        // GIVEN
        val expectedResult = listOf(sampleUserData)
        whenever(repository.getAllUsers(numberOfUsers)).thenReturn(Resource.Success(expectedResult))

        // WHEN
        serviceUnderTest.getAllUsers(numberOfUsers)
        assertTrue(serviceUnderTest.uiState.value.isLoading)
        advanceUntilIdle()

        // THEN
        verify(repository).getAllUsers(numberOfUsers)
        val actualResult = serviceUnderTest.uiState.value

//        val userResponse = actualResult.users
        assertFalse(actualResult.isLoading)
        assertEquals(expectedResult, actualResult.users)
    }

    @Test
    fun `when get user is called and error occured then return error message`() = runTest {
        // GIVEN
        val expectedResult = "There is an error"
        whenever(repository.getAllUsers(numberOfUsers)).thenReturn(Resource.Error(expectedResult))

        // WHEN
        serviceUnderTest.getAllUsers(numberOfUsers)
        assertTrue(serviceUnderTest.uiState.value.isLoading)
        advanceUntilIdle()

        // THEN
        verify(repository).getAllUsers(numberOfUsers)
        val actualResult = serviceUnderTest.uiState.value
        assertFalse(actualResult.isLoading)
        assertEquals(expectedResult, actualResult.errorMessage)
    }

    @Test
    fun `when get user is called and state is loading then verify loading state`() = runTest {
        // GIVEN
        whenever(repository.getAllUsers(numberOfUsers)).thenReturn(Resource.Loading())

        // WHEN
        serviceUnderTest.getAllUsers(numberOfUsers)
        assertTrue(serviceUnderTest.uiState.value.isLoading)
        advanceUntilIdle()

        // THEN
        verify(repository).getAllUsers(numberOfUsers)
        val actualResult = serviceUnderTest.uiState.value
        assertTrue(actualResult.isLoading)
        assertEquals(true, actualResult.isLoading)
    }
}
