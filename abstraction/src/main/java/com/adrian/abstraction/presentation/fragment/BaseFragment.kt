package com.adrian.abstraction.presentation.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun setArguments(args: Bundle?) {
        if (args != null) {
            super.setArguments(Bundle(args).apply {
                putBundle("BUNDLE_ARGS", args) // Wrap the arguments as BUNDLE_ARGS
            })
        } else {
            super.setArguments(null)
        }
    }

    fun setToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)?.title = ""
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }

}