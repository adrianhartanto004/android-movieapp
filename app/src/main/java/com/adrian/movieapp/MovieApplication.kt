package com.adrian.movieapp

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.adrian.abstraction.common.di.baseViewModelModule
import com.adrian.abstraction.common.di.sharedDatabaseModule
import com.adrian.favourites.di.favouriteDomainModule
import com.adrian.favourites.di.favouriteModule
import com.adrian.favourites.di.favouriteViewModelModule
import com.adrian.home.di.homeDomainModule
import com.adrian.home.di.homeModule
import com.adrian.home.di.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MovieApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidContext(this@MovieApplication)
            modules(
                provideModules()
            )
        }
    }

    private fun provideModules(): List<Module> =
        listOf(
            sharedDatabaseModule,
            baseViewModelModule,
            homeModule,
            homeViewModelModule,
            homeDomainModule,
            favouriteModule,
            favouriteViewModelModule,
            favouriteDomainModule
        )
}