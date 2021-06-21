package com.adrian.favourites.di

import com.adrian.favourites.domain.usecase.GetFavouriteMoviesUseCase
import org.koin.dsl.module

val favouriteDomainModule = module {
    single { GetFavouriteMoviesUseCase(get()) }
}