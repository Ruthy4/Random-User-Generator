package com.example.randomusergenerator.di

import com.example.randomusergenerator.navigator.NavigationManager
import com.example.randomusergenerator.navigator.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun provideNavigator(bind: NavigationManagerImpl): NavigationManager
}
