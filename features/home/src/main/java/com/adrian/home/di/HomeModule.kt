package com.adrian.home.di

import android.app.Application
import androidx.room.Room
import com.adrian.abstraction.common.data.SharedDao
import com.adrian.abstraction.common.network.KtorClientFactory
import com.adrian.abstraction.common.network.KtorClientFactoryImpl
import com.adrian.home.data.HomeRepositoryImpl
import com.adrian.home.data.database.HomeDao
import com.adrian.home.data.database.HomeDatabase
import com.adrian.home.data.network.service.HomeApi
import com.adrian.home.domain.repository.HomeRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val homeModule = module {

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

    fun provideHomeApi(httpClient: KtorClientFactory): HomeApi = HomeApi(httpClient)

    fun provideRepository(
        homeApi: HomeApi,
        homeDao: HomeDao,
        sharedDao: SharedDao
    ): HomeRepository {
        return HomeRepositoryImpl(homeApi, homeDao, sharedDao)
    }

    factory<KtorClientFactory> { KtorClientFactoryImpl() }
    single { provideHomeApi(get()) }
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single { provideRepository(get(), get(), get()) }
}