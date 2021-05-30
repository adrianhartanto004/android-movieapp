package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.domain.model.PopularMoviesList
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun getPopularMovies(page: Int): UseCaseResult<PopularMoviesList> {
        return try {
            UseCaseResult.Success(homeRepository.getPopularMovies(page))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}