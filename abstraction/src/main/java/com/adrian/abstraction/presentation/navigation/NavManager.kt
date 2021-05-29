package com.adrian.abstraction.presentation.navigation

import androidx.navigation.NavDirections

interface NavManager {
    fun navigate(navDirections: NavDirections)
    fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit)
}