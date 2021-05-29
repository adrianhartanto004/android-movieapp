package com.adrian.search.presentation.fragment

import android.os.Bundle
import android.view.View
import com.adrian.abstraction.presentation.fragment.BaseFragment
import com.adrian.search.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
