package com.adrian.favourites.di

import com.adrian.abstraction.common.data.SharedDao
import com.adrian.favourites.data.FavouriteRepositoryImpl
import com.adrian.favourites.domain.repository.FavouriteRepository
import org.koin.dsl.module

val favouriteModule = module {
    fun provideRepository(sharedDao: SharedDao): FavouriteRepository {
        return FavouriteRepositoryImpl(sharedDao)
    }
    single { provideRepository(get()) }
}