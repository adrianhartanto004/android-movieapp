package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.data.network.model.authorreview.AuthorReviewListJson
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException

class GetAuthorReviewsUseCase(private val homeRepository: HomeRepository) {
    suspend fun getAuthorReviews(movieId: Int, page: Int): UseCaseResult<AuthorReviewListJson> {
        return try {
            UseCaseResult.Success(homeRepository.getAuthorReviews(movieId, page))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}