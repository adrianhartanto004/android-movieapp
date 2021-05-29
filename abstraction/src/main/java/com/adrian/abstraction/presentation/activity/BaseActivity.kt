package com.adrian.abstraction.presentation.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.adrian.abstraction.extension.hideKeyboardWhenScreenClicked

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        hideKeyboardWhenScreenClicked(ev)
        return super.dispatchTouchEvent(ev)
    }
}