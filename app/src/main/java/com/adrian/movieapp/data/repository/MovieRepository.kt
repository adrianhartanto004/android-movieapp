package com.adrian.movieapp.data.repository

import android.os.Handler
import com.adrian.movieapp.data.network.model.Result
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor

private const val url =
    "https://api.themoviedb.org/3/movie/now_playing?api_key=7cc18fb1825507e7ea16fafe18153632&language=en-US&page=1"

sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
}

class MovieRepository(
    private val executor: Executor,
    private val resultHandler: Handler
) {
    fun getPopularMovies(callback: (ApiResult<List<Result>>) -> Unit) {
        executor.execute {
            try {
                val response = makeSynchronousLoginRequest()
                resultHandler.post { callback(response) }
            } catch (e: Exception) {
                val errorResult = ApiResult.Error(e)
                resultHandler.post { callback(errorResult) }
            }
        }
    }

    private fun makeSynchronousLoginRequest(): ApiResult<List<Result>> {
        val resultArrayList = arrayListOf<Result>()
        val url = URL(url)
        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            doInput = true

            val bR = BufferedReader(InputStreamReader(inputStream))
            var line: String? = ""

            val responseStrBuilder = StringBuilder()
            while (bR.readLine().also { line = it } != null) {
                responseStrBuilder.append(line)
            }
            val result = JSONObject(responseStrBuilder.toString())
            val jArray = result.getJSONArray("results")

            for (i in 0 until jArray.length()) {
                try {
                    val oneObject: JSONObject = jArray.getJSONObject(i)
                    // Pulling items from the array
                    val originalTitle = oneObject.getString("original_title")
                    val overview = oneObject.getString("overview")
                    resultArrayList.add(Result(originalTitle, overview))
                } catch (e: JSONException) {
                    return ApiResult.Error(e)
                }
            }
        }
        return ApiResult.Success(resultArrayList)
    }
}