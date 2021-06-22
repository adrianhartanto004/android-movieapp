package com.adrian.home.data.network.service

import com.adrian.abstraction.common.network.KtorClientFactory
import com.adrian.abstraction.common.network.constant.KeyProvider
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.data.network.model.genre.GenreListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.data.network.model.nowplayingmovies.NowPlayingMoviesListJson
import com.adrian.home.data.network.model.popularmovies.PopularMovieListJson
import com.adrian.home.data.network.model.recommendedmovies.RecommendedMoviesListJson
import io.ktor.client.request.*

class HomeApi(private val ktorClientFactory: KtorClientFactory) {
    suspend fun getPopularMovies(page: Int): PopularMovieListJson =
        ktorClientFactory.createClient().use {
            it.get("movie/popular") {
                parameter("page", page)
            }
        }

    suspend fun getNowPlayingMovies(page: Int): NowPlayingMoviesListJson =
        ktorClientFactory.createClient().use {
            it.get("movie/now_playing") {
                parameter("page", page)
            }
        }

    suspend fun getGenres(): GenreListJson =
        ktorClientFactory.createClient().use {
            it.get("genre/movie/list")
        }

    suspend fun getMovieDetail(movieId: Int): MovieDetailResponseJson =
        ktorClientFactory.createClient().use {
            it.get("movie/$movieId") {
                parameter(KeyProvider.API_PARAM, KeyProvider.API_KEY)
            }
        }

    suspend fun getMovieCredits(movieId: Int): MovieCreditListJson =
        ktorClientFactory.createClient().use {
            it.get("movie/$movieId/credits")
        }

    suspend fun getMoviePhotos(movieId: Int): MoviesPhotoListJson =
        ktorClientFactory.createClient().use {
            it.get("movie/$movieId/images")
        }

    suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendedMoviesListJson =
        ktorClientFactory.createClient().use {
            it.get("movie/$movieId/recommendations") {
                parameter("page", page)
            }
        }

    suspend fun getAuthorReviews(movieId: Int, page: Int): AuthorReviewListJson =
        ktorClientFactory.createClient().use {
            it.get("movie/$movieId/reviews") {
                parameter("page", page)
            }
        }
}