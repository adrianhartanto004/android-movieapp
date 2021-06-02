package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.data.network.model.moviecredits.MovieCreditListJson
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun getMovieCredits(movieId: Int): UseCaseResult<MovieCreditListJson> {
        return try {
            UseCaseResult.Success(homeRepository.getMovieCredits(movieId))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}