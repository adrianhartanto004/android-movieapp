package com.adrian.abstraction.presentation.navigation

import androidx.navigation.NavDirections
import javax.inject.Inject

class NavManagerImpl @Inject constructor() : NavManager {
    private var navEventListener: ((navDirections: NavDirections) -> Unit)? = null

    override fun navigate(navDirections: NavDirections) {
        navEventListener?.invoke(navDirections)
    }

    override fun setOnNavEvent(navEventListener: (navDirections: NavDirections) -> Unit) {
        this.navEventListener = navEventListener
    }
}
