package com.adrian.abstraction.common.di

import com.adrian.abstraction.presentation.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseViewModelModule = module {
    viewModel { BaseViewModel() }
}