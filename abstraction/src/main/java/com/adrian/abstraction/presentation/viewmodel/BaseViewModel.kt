package com.adrian.abstraction.presentation.viewmodel

import androidx.lifecycle.ViewModel

open class BaseViewModel() : ViewModel() {
    var apiDataReceived = false
}