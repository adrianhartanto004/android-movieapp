package com.adrian.abstraction.common.state

sealed class Result<out T> {
    data class Success<T>(val data: T?) : Result<T>()
    data class Error(val e: Throwable) : Result<Nothing>()
}

infix fun <T> Result<T>.onSuccess(onSuccess: Result.Success<T>.() -> Unit): Result<T> {
    return when (this) {
        is Result.Success -> {
            onSuccess(this)
            this
        }
        else -> this
    }
}

infix fun <T> Result<T>.onError(onError: Result.Error.() -> Unit): Result<T> {
    return when (this) {
        is Result.Error -> {
            onError(this)
            this
        }
        else -> this
    }
}