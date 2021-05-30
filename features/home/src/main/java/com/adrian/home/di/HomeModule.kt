package com.adrian.home.di

import android.app.Application
import androidx.room.Room
import com.adrian.abstraction.common.network.RetrofitClient
import com.adrian.home.data.HomeRepositoryImpl
import com.adrian.home.data.database.HomeDao
import com.adrian.home.data.database.HomeDatabase
import com.adrian.home.data.network.service.HomeRetrofitService
import com.adrian.home.domain.repository.HomeRepository
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
    fun provideRetrofit(): RetrofitClient = RetrofitClient

    @Provides
    @Singleton
    fun provideHomeRetrofitService(
        retrofitClient: RetrofitClient,
        context: Application
    ): HomeRetrofitService =
        retrofitClient.retrofit(context)
            .create(HomeRetrofitService::class.java)

    @Provides
    @Singleton
    internal fun provideDatabase(context: Application): HomeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HomeDatabase::class.java,
            HomeDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    internal fun provideDao(homeDatabase: HomeDatabase): HomeDao {
        return homeDatabase.homeDao()
    }

    @Singleton
    @Provides
    internal fun provideRepository(
        homeRetrofitService: HomeRetrofitService,
        homeDao: HomeDao
    ): HomeRepository {
        return HomeRepositoryImpl(homeRetrofitService, homeDao)
    }
}