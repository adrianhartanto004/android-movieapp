package com.adrian.favourites.di

import com.adrian.abstraction.common.data.SharedDao
import com.adrian.favourites.data.FavouriteRepositoryImpl
import com.adrian.favourites.domain.repository.FavouriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {

    @Singleton
    @Provides
    internal fun provideRepository(sharedDao: SharedDao): FavouriteRepository {
        return FavouriteRepositoryImpl(sharedDao)
    }
}