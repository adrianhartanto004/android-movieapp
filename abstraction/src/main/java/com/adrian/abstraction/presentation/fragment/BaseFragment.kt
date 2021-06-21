package com.adrian.abstraction.presentation.fragment

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    fun setToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)?.title = ""
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }

}