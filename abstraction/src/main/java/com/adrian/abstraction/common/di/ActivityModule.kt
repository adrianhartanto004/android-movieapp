package com.adrian.abstraction.common.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.adrian.abstraction.presentation.navigation.NavManager
import com.adrian.abstraction.presentation.navigation.NavManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    companion object {
        @Provides
        fun provideAppCompatActivity(activity: Activity) = activity as AppCompatActivity
    }

}