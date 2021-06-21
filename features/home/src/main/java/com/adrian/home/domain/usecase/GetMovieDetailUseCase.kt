package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.data.network.model.moviedetail.MovieDetailResponseJson
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase  (private val homeRepository: HomeRepository) {
    suspend fun getMovieDetail(movieId: Int): UseCaseResult<MovieDetailResponseJson> {
        return try {
            UseCaseResult.Success(homeRepository.getMovieDetail(movieId))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}