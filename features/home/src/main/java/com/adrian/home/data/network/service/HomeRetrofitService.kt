package com.adrian.home.data.network.service

import com.adrian.home.data.network.model.genre.GenreListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.data.network.model.nowplayingmovies.NowPlayingMoviesListJson
import com.adrian.home.data.network.model.popularmovies.PopularMovieListJson
import com.adrian.home.data.network.model.recommendedmovies.RecommendedMoviesListJson
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

    @GET("movie/{movieId}/images")
    suspend fun getMoviePhotos(@Path("movieId") movieId: Int): MoviesPhotoListJson

    @GET("movie/{movieId}/recommendations")
    suspend fun getRecommendedMovies(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int
    ): RecommendedMoviesListJson

}