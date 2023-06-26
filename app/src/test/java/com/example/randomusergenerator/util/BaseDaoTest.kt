package com.example.randomusergenerator.util

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.randomusergenerator.data.local.UserDatabase
import com.example.randomusergenerator.data.local.dao.UserDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

abstract class BaseDaoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutinesRule = MainCoroutineRule()

    private lateinit var db: UserDatabase
    lateinit var dao: UserDao

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java)
            .setTransactionExecutor(coroutinesRule.testDispatcher.asExecutor())
            .allowMainThreadQueries()
            .build()
        dao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}