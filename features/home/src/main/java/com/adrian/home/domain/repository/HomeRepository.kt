package com.adrian.home.domain.repository

import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.model.popularmovies.PopularMoviesList
import com.adrian.home.domain.model.recommendedmovies.RecommendedMoviesList
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPopularMovies(page: Int): Flow<PopularMoviesList>
    fun getNowPlayingMovies(page: Int): Flow<NowPlayingMoviesList>
    fun getGenres(): Flow<List<Genre>>
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponseJson?
    suspend fun getMovieCredits(movieId: Int): MovieCreditListJson?
    suspend fun getMoviePhotos(movieId: Int): MoviesPhotoListJson?
    suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendedMoviesList?
    suspend fun getAuthorReviews(movieId: Int, page: Int): AuthorReviewListJson?
    suspend fun isFavouriteMovieExist(movieId: Int): Boolean
    suspend fun addFavouriteMovie(favouriteMovie: FavouriteMovie): Boolean
}
