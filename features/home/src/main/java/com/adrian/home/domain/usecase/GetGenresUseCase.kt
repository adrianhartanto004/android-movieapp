package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException

class GetGenresUseCase(private val homeRepository: HomeRepository) {
    suspend fun getGenres(): UseCaseResult<List<Genre>> {
        return try {
            UseCaseResult.Success(homeRepository.getGenres())
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}