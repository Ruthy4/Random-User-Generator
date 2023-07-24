package com.example.randomusergenerator.user.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.navigator.NavigationManager
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
    lateinit var navigationManager: NavigationManager

    @Mock
    lateinit var repository: UserRepository

    private val numberOfUsers = 50

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        serviceUnderTest = UserViewModel(repository, navigationManager)
    }

    @Test
    fun `when getAllUsers is called, and state is successful then return users`() = runTest {
        val expectedResult = listOf(sampleUserData)
        whenever(repository.getAllUsers(numberOfUsers)).thenReturn(Resource.Success(expectedResult))

        serviceUnderTest.getAllUsers()
        assertTrue(serviceUnderTest.uiState.value.isLoading)
        advanceUntilIdle()

        val actualResult = serviceUnderTest.uiState.value

        assertFalse(actualResult.isLoading)
        assertEquals(expectedResult, actualResult.users)
    }

    @Test
    fun `when getAllUsers is called and error occurred then return error message`() = runTest {
        val expectedResult = "There is an error"
        whenever(repository.getAllUsers(numberOfUsers)).thenReturn(Resource.Error(expectedResult))

        serviceUnderTest.getAllUsers()
        assertTrue(serviceUnderTest.uiState.value.isLoading)
        advanceUntilIdle()

        val actualResult = serviceUnderTest.uiState.value
        assertFalse(actualResult.isLoading)
        assertEquals(expectedResult, actualResult.errorMessage)
    }

    @Test
    fun `when getAllUsers is called and state is loading then verify loading state`() = runTest {
        whenever(repository.getAllUsers(numberOfUsers)).thenReturn(Resource.Loading())

        serviceUnderTest.getAllUsers()
        assertTrue(serviceUnderTest.uiState.value.isLoading)
        advanceUntilIdle()

        val actualResult = serviceUnderTest.uiState.value
        assertTrue(actualResult.isLoading)
        assertEquals(true, actualResult.isLoading)
    }
}
