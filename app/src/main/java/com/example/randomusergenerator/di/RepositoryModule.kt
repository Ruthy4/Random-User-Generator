package com.example.randomusergenerator.di

import com.example.randomusergenerator.data.local.UserDatabase
import com.example.randomusergenerator.data.remote.ApiService
import com.example.randomusergenerator.data.repository.UserRepositoryImpl
import com.example.randomusergenerator.domain.repository.UserRepository
import com.example.randomusergenerator.utils.Constants.BASE_URL
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
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
