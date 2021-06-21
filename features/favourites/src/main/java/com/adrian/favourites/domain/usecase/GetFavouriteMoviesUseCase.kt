package com.adrian.favourites.domain.usecase

import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.favourites.domain.repository.FavouriteRepository
import java.io.IOException

class GetFavouriteMoviesUseCase(private val favouriteRepository: FavouriteRepository) {
    suspend fun getFavouriteMovies(): UseCaseResult<List<FavouriteMovie>> {
        return try {
            UseCaseResult.Success(favouriteRepository.getAllFavouriteMovies())
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}