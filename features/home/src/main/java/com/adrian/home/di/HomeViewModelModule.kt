package com.adrian.home.di

import com.adrian.home.presentation.home.viewmodel.HomeViewModel
import com.adrian.home.presentation.moviedetail.viewmodel.MovieDetailViewModel
import com.adrian.home.presentation.popularmovieslist.viewmodel.PopularMoviesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel {
        MovieDetailViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel { PopularMoviesListViewModel(get(), get()) }
}