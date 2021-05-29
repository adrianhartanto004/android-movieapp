package com.adrian.abstraction.common.network

import android.app.Application
import com.adrian.abstraction.common.network.constant.UrlProvider
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Singleton
object RetrofitClient {
    fun retrofit(context: Application): Retrofit {
        val chuckInterceptorClient = OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .build()

        return Retrofit.Builder()
            .baseUrl(UrlProvider.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(chuckInterceptorClient)
            .build()
    }
}