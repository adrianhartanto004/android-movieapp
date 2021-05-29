package com.adrian.movieapp

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.adrian.abstraction.presentation.activity.BaseActivity
import com.adrian.abstraction.presentation.navigation.NavManager
import com.adrian.movieapp.databinding.ActivityNavHostBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NavHostActivity : BaseActivity() {

    private lateinit var binding: ActivityNavHostBinding

    private val navController get() = Navigation.findNavController(this, R.id.fragment_nav_host)

    @Inject
    lateinit var navManager: NavManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNavigation()
        initNavManager()
    }

    private fun initBottomNavigation() {
        binding.btmNavMain.setupWithNavController(navController)
        binding.btmNavMain.setOnNavigationItemReselectedListener {}
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav_host)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.findNavController()?.navigate(it)
        }
    }
}



