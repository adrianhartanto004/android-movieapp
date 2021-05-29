package com.adrian.abstraction.common.state

import com.adrian.abstraction.common.network.response.ErrorResponse

sealed class ApiResult<out T> {
    data class Success<out T>(val value: T) : ApiResult<T>()
    data class GenericError(val code: Int? = null, val errorResponse: ErrorResponse? = null) : ApiResult<Nothing>()
    data class NetworkError(val e: Throwable) : ApiResult<Nothing>()
}