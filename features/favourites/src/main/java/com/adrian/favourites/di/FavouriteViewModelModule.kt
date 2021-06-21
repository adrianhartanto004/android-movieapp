package com.adrian.favourites.di

import com.adrian.favourites.presentation.viewmodel.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteViewModelModule = module {
    viewModel { FavouriteViewModel(get(), get()) }
}