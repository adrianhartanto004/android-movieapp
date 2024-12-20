package com.adrian.abstraction.extension

import com.adrian.abstraction.common.network.constant.NetworkErrors.NETWORK_ERROR_UNKNOWN
import com.adrian.abstraction.common.network.response.ErrorResponse
import com.adrian.abstraction.common.state.ApiResult
import com.bumptech.glide.load.HttpException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ApiResult<T> {
    return withContext(dispatcher) {
        try {
            ApiResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is IOException -> {
                    ApiResult.NetworkError(throwable)
                }
                is HttpException -> {
                    val code = throwable.statusCode
//                    val errorResponse = convertErrorBody(throwable)
                    ApiResult.GenericError(code, ErrorResponse(0, throwable.message ?: "", false))
                }
                else -> {
                    ApiResult.GenericError(null, ErrorResponse(0, NETWORK_ERROR_UNKNOWN))
                }
            }
        }
    }
}


//private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
//    return try {
//        throwable.response()?.errorBody()?.source()?.let {
//            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
//            moshiAdapter.fromJson(it)
//        }
//    } catch (exception: Exception) {
//        null
//    }
//}