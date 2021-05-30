package com.adrian.home.data.network.service

import com.adrian.home.data.network.model.popularmovies.PopularMovieListJson
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeRetrofitService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): PopularMovieListJson
}