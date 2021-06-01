package com.adrian.abstraction.common.state

sealed class UseCaseResult<out T> {
    data class Success<T>(val data: T?) : UseCaseResult<T>()
    data class Error(val e: Throwable) : UseCaseResult<Nothing>()
}

infix fun <T> UseCaseResult<T>.onSuccess(onSuccess: UseCaseResult.Success<T>.() -> Unit): UseCaseResult<T> {
    return when (this) {
        is UseCaseResult.Success -> {
            onSuccess(this)
            this
        }
        else -> this
    }
}

infix fun <T> UseCaseResult<T>.onError(onError: UseCaseResult.Error.() -> Unit): UseCaseResult<T> {
    return when (this) {
        is UseCaseResult.Error -> {
            onError(this)
            this
        }
        else -> this
    }
}