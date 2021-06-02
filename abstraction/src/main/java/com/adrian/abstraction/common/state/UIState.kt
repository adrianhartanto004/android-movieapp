package com.adrian.abstraction.common.state

import com.adrian.abstraction.common.network.enum.ErrorStatus

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<T>(val data: T?) : UIState<T>()
    data class Failure(val errorStatus: ErrorStatus, val message: String) : UIState<Nothing>()
}

infix fun <T> UIState<T>.onLoading(onLoading: UIState.Loading.() -> Unit): UIState<T> {
    return when (this) {
        is UIState.Loading -> {
            onLoading(this)
            this
        }
        else -> {
            this
        }
    }
}

infix fun <T> UIState<T>.onSuccess(onSuccess: UIState.Success<T>.() -> Unit): UIState<T> {
    return when (this) {
        is UIState.Success -> {
            onSuccess(this)
            this
        }
        else -> {
            this
        }
    }
}

infix fun <T> UIState<T>.onFailure(onError: UIState.Failure.() -> Unit): UIState<T> {
    return when (this) {
        is UIState.Failure -> {
            onError(this)
            this
        }
        else -> {
            this
        }
    }
}