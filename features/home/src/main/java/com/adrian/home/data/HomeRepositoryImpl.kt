package com.adrian.home.data

import com.adrian.abstraction.common.state.ApiResult
import com.adrian.abstraction.extension.safeApiCall
import com.adrian.home.data.database.HomeDao
import com.adrian.home.data.database.model.genre.toDomainModel
import com.adrian.home.data.database.model.nowplayingmovies.toDomainModel
import com.adrian.home.data.database.model.popularmovies.toDomainModel
import com.adrian.home.data.network.model.genre.toDomainModel
import com.adrian.home.data.network.model.genre.toEntity
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.data.network.model.nowplayingmovies.toDomainModel
import com.adrian.home.data.network.model.nowplayingmovies.toEntity
import com.adrian.home.data.network.model.popularmovies.toDomainModel
import com.adrian.home.data.network.model.popularmovies.toEntity
import com.adrian.home.data.network.model.recommendedmovies.RecommendedMoviesListJson
import com.adrian.home.data.network.model.recommendedmovies.toDomainModel
import com.adrian.home.data.network.service.HomeRetrofitService
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.model.popularmovies.PopularMoviesList
import com.adrian.home.domain.model.recommendedmovies.RecommendedMoviesList
import com.adrian.home.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class HomeRepositoryImpl(
    private val homeRetrofitService: HomeRetrofitService,
    private val homeDao: HomeDao
) : HomeRepository {
    override suspend fun getPopularMovies(page: Int): PopularMoviesList {
        try {
            return when (val response =
                safeApiCall(Dispatchers.IO) { homeRetrofitService.getPopularMovies(page) }) {
                is ApiResult.Success -> {
                    val popularMovies = response.value
                    popularMovies.results
                        .map { it.toEntity() }
                        .let { homeDao.addPopularMovies(it) }

                    popularMovies.toDomainModel()
                }
                is ApiResult.GenericError -> {
                    return homeDao.getAllPopularMovies().toDomainModel()
                }
                is ApiResult.NetworkError -> {
                    return homeDao.getAllPopularMovies().toDomainModel()
                }
            }
        } catch (e: IOException) {
            return homeDao.getAllPopularMovies().toDomainModel()
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): NowPlayingMoviesList {
        try {
            return when (val response =
                safeApiCall(Dispatchers.IO) { homeRetrofitService.getNowPlayingMovies(page) }) {
                is ApiResult.Success -> {
                    val nowPlayingMovies = response.value
                    nowPlayingMovies.results
                        .map { it.toEntity() }
                        .let { homeDao.addNowPlayingMovies(it) }

                    nowPlayingMovies.toDomainModel()
                }
                is ApiResult.GenericError -> {
                    return homeDao.getAllNowPlayingMovies().toDomainModel()
                }
                is ApiResult.NetworkError -> {
                    return homeDao.getAllNowPlayingMovies().toDomainModel()
                }
            }
        } catch (e: IOException) {
            return homeDao.getAllNowPlayingMovies().toDomainModel()
        }
    }

    override suspend fun getGenres(): List<Genre> {
        try {
            if (homeDao.getAllGenres().isNotEmpty()) {
                return homeDao.getAllGenres().map { it.toDomainModel() }
            } else {
                return when (
                    val response =
                        safeApiCall(Dispatchers.IO) { homeRetrofitService.getGenres() }) {
                    is ApiResult.Success -> {
                        val genres = response.value.genres
                        genres
                            .map { it.toEntity() }
                            .let { homeDao.addGenres(it) }

                        genres.map { it.toDomainModel() }
                    }
                    is ApiResult.GenericError -> {
                        return homeDao.getAllGenres().map { it.toDomainModel() }
                    }
                    is ApiResult.NetworkError -> {
                        return homeDao.getAllGenres().map { it.toDomainModel() }
                    }
                }
            }
        } catch (e: IOException) {
            return homeDao.getAllGenres().map { it.toDomainModel() }
        }
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailResponseJson? {
        try {
            return when (val response =
                safeApiCall(Dispatchers.IO) { homeRetrofitService.getMovieDetail(movieId) }) {
                is ApiResult.Success -> {
                    response.value
                }
                is ApiResult.GenericError -> {
                    null
                }
                is ApiResult.NetworkError -> {
                    null
                }
            }
        } catch (e: IOException) {
            return null
        }
    }

    override suspend fun getMovieCredits(movieId: Int): MovieCreditListJson? {
        try {
            return when (val response =
                safeApiCall(Dispatchers.IO) { homeRetrofitService.getMovieCredits(movieId) }) {
                is ApiResult.Success -> {
                    response.value
                }
                is ApiResult.GenericError -> {
                    null
                }
                is ApiResult.NetworkError -> {
                    null
                }
            }
        } catch (e: IOException) {
            return null
        }
    }

    override suspend fun getMoviePhotos(movieId: Int): MoviesPhotoListJson? {
        try {
            return when (val response =
                safeApiCall(Dispatchers.IO) { homeRetrofitService.getMoviePhotos(movieId) }) {
                is ApiResult.Success -> {
                    response.value
                }
                is ApiResult.GenericError -> {
                    null
                }
                is ApiResult.NetworkError -> {
                    null
                }
            }
        } catch (e: IOException) {
            return null
        }
    }

    override suspend fun getRecommendedMovies(movieId: Int, page: Int): RecommendedMoviesList? {
        try {
            return when (val response =
                safeApiCall(Dispatchers.IO) { homeRetrofitService.getRecommendedMovies(movieId, page) }) {
                is ApiResult.Success -> {
                    response.value.toDomainModel()
                }
                is ApiResult.GenericError -> {
                    null
                }
                is ApiResult.NetworkError -> {
                    null
                }
            }
        } catch (e: IOException) {
            return null
        }
    }
}