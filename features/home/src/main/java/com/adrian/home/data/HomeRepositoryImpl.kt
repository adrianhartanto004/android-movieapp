package com.adrian.home.data

import com.adrian.abstraction.common.state.ApiResult
import com.adrian.abstraction.extension.safeApiCall
import com.adrian.home.data.database.HomeDao
import com.adrian.home.data.database.model.popularmovies.toDomainModel
import com.adrian.home.data.network.model.popularmovies.toDomainModel
import com.adrian.home.data.network.model.popularmovies.toEntity
import com.adrian.home.data.network.service.HomeRetrofitService
import com.adrian.home.domain.model.PopularMoviesList
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
}