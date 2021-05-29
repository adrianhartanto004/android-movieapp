package com.adrian.home.di

import android.app.Application
import com.adrian.abstraction.common.network.RetrofitClient
import com.adrian.home.data.network.service.HomeRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Singleton
    @Provides
    fun retrofit(): RetrofitClient = RetrofitClient

    @Provides
    @Singleton
    fun homeRetrofitService(
        retrofitClient: RetrofitClient,
        context: Application
    ): HomeRetrofitService =
        retrofitClient.retrofit(context)
            .create(HomeRetrofitService::class.java)
}