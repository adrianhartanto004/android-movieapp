package com.adrian.home.domain.repository

import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.data.network.model.recommendedmovies.RecommendedMoviesListJson
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.model.popularmovies.PopularMoviesList
import com.adrian.home.domain.model.recommendedmovies.RecommendedMoviesList

interface HomeRepository {
    suspend fun getPopularMovies(page: Int): PopularMoviesList
    suspend fun getNowPlayingMovies(page: Int): NowPlayingMoviesList
    suspend fun getGenres(): List<Genre>
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponseJson?
    suspend fun getMovieCredits(movieId: Int): MovieCreditListJson?
    suspend fun getMoviePhotos(movieId: Int): MoviesPhotoListJson?
    suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendedMoviesList?
}