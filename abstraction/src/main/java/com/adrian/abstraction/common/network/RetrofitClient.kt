package com.adrian.abstraction.common.network

import android.app.Application
import com.adrian.abstraction.common.network.constant.UrlProvider
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object RetrofitClient {

    fun retrofit(context: Application): Retrofit {

        val requestInterceptor: Interceptor by lazy {
            RequestInterceptor()
        }

        val okHttpClientInstance: OkHttpClient by lazy {
            val client = OkHttpClient.Builder()
            client.addInterceptor(ChuckInterceptor(context))
            client.addInterceptor(requestInterceptor)
            client.build()
        }

        return Retrofit.Builder()
            .baseUrl(UrlProvider.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClientInstance)
            .build()
    }
}