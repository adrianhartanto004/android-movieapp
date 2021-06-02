package com.adrian.home.data.network.service

import com.adrian.home.data.network.model.genre.GenreListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.nowplayingmovies.NowPlayingMoviesListJson
import com.adrian.home.data.network.model.popularmovies.PopularMovieListJson
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeRetrofitService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): PopularMovieListJson

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): NowPlayingMoviesListJson

    @GET("genre/movie/list")
    suspend fun getGenres(): GenreListJson

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieDetailResponseJson

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCredits(@Path("movieId") movieId: Int): MovieCreditListJson

}