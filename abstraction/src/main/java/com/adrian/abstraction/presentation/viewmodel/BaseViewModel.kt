package com.adrian.abstraction.presentation.viewmodel

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy

open class BaseViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val arguments get() = savedStateHandle.get<Bundle>("BUNDLE_ARGS")

    @MainThread
    inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("ViewModel $this has null arguments")
    }
}