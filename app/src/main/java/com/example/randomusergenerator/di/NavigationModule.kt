package com.example.randomusergenerator.di

import com.example.randomusergenerator.navigator.NavigationManager
import com.example.randomusergenerator.navigator.NavigationManagerImpl
import com.example.randomusergenerator.view.FeatureApi
import com.example.randomusergenerator.view.UserFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun provideNavigator(bind: NavigationManagerImpl): NavigationManager

    @Binds
    @Singleton
    fun provideFeatureApi(bind: UserFeatureImpl): FeatureApi
}
