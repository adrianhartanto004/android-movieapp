package com.adrian.abstraction.common.di

import android.app.Application
import androidx.room.Room
import com.adrian.abstraction.common.data.SharedDao
import com.adrian.abstraction.common.data.SharedDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sharedDatabaseModule = module {

    fun provideSharedDatabase(context: Application): SharedDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SharedDatabase::class.java,
            SharedDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    fun provideSharedDao(sharedDatabase: SharedDatabase): SharedDao {
        return sharedDatabase.sharedDao()
    }

    single { provideSharedDatabase(androidApplication()) }
    single { provideSharedDao(get()) }
}