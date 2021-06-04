package com.adrian.abstraction.common.di

import android.app.Application
import androidx.room.Room
import com.adrian.abstraction.common.data.SharedDao
import com.adrian.abstraction.common.data.SharedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedDatabaseModule {

    @Provides
    @Singleton
    internal fun provideDatabase(context: Application): SharedDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SharedDatabase::class.java,
            SharedDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    @Provides
    internal fun provideDao(sharedDatabase: SharedDatabase): SharedDao {
        return sharedDatabase.sharedDao()
    }
}