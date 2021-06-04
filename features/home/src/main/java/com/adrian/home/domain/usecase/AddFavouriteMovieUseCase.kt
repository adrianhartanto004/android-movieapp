package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class AddFavouriteMovieUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun addFavouriteMovie(favouriteMovie: FavouriteMovie): UseCaseResult<Boolean> {
        return try {
            UseCaseResult.Success(homeRepository.addFavouriteMovie(favouriteMovie))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}