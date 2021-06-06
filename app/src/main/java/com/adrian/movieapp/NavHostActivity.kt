package com.adrian.movieapp

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.adrian.abstraction.presentation.activity.BaseActivity
import com.adrian.movieapp.databinding.ActivityNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

class NavHostActivity : BaseActivity() {

    private lateinit var binding: ActivityNavHostBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragment_nav_host
        ) as NavHostFragment
        navController = navHostFragment.navController

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.apply {
            btmNavMain.setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}



