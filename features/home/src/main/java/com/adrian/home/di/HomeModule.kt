package com.adrian.home.di

import android.app.Application
import androidx.room.Room
import com.adrian.abstraction.common.data.SharedDao
import com.adrian.abstraction.common.network.RetrofitClient
import com.adrian.home.data.HomeRepositoryImpl
import com.adrian.home.data.database.HomeDao
import com.adrian.home.data.database.HomeDatabase
import com.adrian.home.data.network.service.HomeRetrofitService
import com.adrian.home.domain.repository.HomeRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val homeModule = module {

    fun provideRetrofit(): RetrofitClient = RetrofitClient

    fun provideHomeRetrofitService(
        retrofitClient: RetrofitClient,
        context: Application
    ): HomeRetrofitService =
        retrofitClient.retrofit(context)
            .create(HomeRetrofitService::class.java)

    fun provideDatabase(context: Application): HomeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HomeDatabase::class.java,
            HomeDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    fun provideDao(homeDatabase: HomeDatabase): HomeDao {
        return homeDatabase.homeDao()
    }

    fun provideRepository(
        homeRetrofitService: HomeRetrofitService,
        homeDao: HomeDao,
        sharedDao: SharedDao
    ): HomeRepository {
        return HomeRepositoryImpl(homeRetrofitService, homeDao, sharedDao)
    }

    single { provideRetrofit() }
    single { provideHomeRetrofitService(get(), androidApplication()) }
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single { provideRepository(get(), get(), get()) }
}