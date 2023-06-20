package com.example.randomusergenerator.di

import com.example.randomusergenerator.data.local.UserDatabase
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.data.repository.UserRepositoryImpl
import com.example.randomusergenerator.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        database: UserDatabase,
        api: ApiService
    ): UserRepository {
        return UserRepositoryImpl(api, database)
    }
}
