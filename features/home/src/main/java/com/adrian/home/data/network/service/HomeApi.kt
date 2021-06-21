package com.adrian.home.data.network.service

import com.adrian.abstraction.common.network.constant.KeyProvider
import com.adrian.abstraction.common.network.constant.UrlProvider
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.data.network.model.genre.GenreListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.data.network.model.nowplayingmovies.NowPlayingMoviesListJson
import com.adrian.home.data.network.model.popularmovies.PopularMovieListJson
import com.adrian.home.data.network.model.recommendedmovies.RecommendedMoviesListJson
import io.ktor.client.*
import io.ktor.client.request.*

class HomeApi(private val client: HttpClient) {
    suspend fun getPopularMovies(page: Int): PopularMovieListJson =
        client.get("${UrlProvider.BASE_URL}movie/popular") {
            parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
            parameter("page", page)
        }

    suspend fun getNowPlayingMovies(page: Int): NowPlayingMoviesListJson =
        client.get("${UrlProvider.BASE_URL}movie/now_playing") {
            parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
            parameter("page", page)
        }

    suspend fun getGenres(): GenreListJson = client.get("${UrlProvider.BASE_URL}genre/movie/list")

    suspend fun getMovieDetail(movieId: Int): MovieDetailResponseJson =
        client.get("${UrlProvider.BASE_URL}movie/$movieId") {
            parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
        }

    suspend fun getMovieCredits(movieId: Int): MovieCreditListJson =
        client.get("${UrlProvider.BASE_URL}movie/$movieId/credits") {
            parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
        }

    suspend fun getMoviePhotos(movieId: Int): MoviesPhotoListJson =
        client.get("${UrlProvider.BASE_URL}movie/$movieId/images") {
            parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
        }

    suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendedMoviesListJson =
        client.get("${UrlProvider.BASE_URL}movie/$movieId/recommendations") {
            parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
            parameter("page", page)
        }

    suspend fun getAuthorReviews(movieId: Int, page: Int): AuthorReviewListJson =
        client.get("${UrlProvider.BASE_URL}movie/$movieId/reviews") {
            parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
            parameter("page", page)
        }
}