package com.adrian.home.presentation.fragment

import android.os.Bundle
import android.view.View
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.home.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
