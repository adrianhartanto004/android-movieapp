package com.adrian.abstraction.common.di

import com.adrian.abstraction.presentation.navigation.NavManager
import com.adrian.abstraction.presentation.navigation.NavManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindsNavManager(navManagerImpl: NavManagerImpl): NavManager

}