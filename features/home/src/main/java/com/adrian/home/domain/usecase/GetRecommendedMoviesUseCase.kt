package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.domain.model.recommendedmovies.RecommendedMoviesList
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class GetRecommendedMoviesUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun getRecommendedMovies(movieId: Int, page: Int): UseCaseResult<RecommendedMoviesList> {
        return try {
            UseCaseResult.Success(homeRepository.getRecommendedMovies(movieId, page))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}