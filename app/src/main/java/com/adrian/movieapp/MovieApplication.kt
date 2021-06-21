package com.adrian.movieapp

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.adrian.abstraction.common.di.sharedDatabaseModule
import com.adrian.favourites.di.favouriteDomainModule
import com.adrian.favourites.di.favouriteModule
import com.adrian.favourites.di.favouriteViewModelModule
import com.adrian.home.di.homeDomainModule
import com.adrian.home.di.homeModule
import com.adrian.home.di.homeViewModelModule
import org.koin.core.context.startKoin

class MovieApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            modules(
                listOf(
                    sharedDatabaseModule,
                    homeModule,
                    homeViewModelModule,
                    homeDomainModule,
                    favouriteModule,
                    favouriteViewModelModule,
                    favouriteDomainModule
                )
            )
        }
    }
}