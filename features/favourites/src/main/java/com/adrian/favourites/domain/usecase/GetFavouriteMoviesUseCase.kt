package com.adrian.favourites.domain.usecase

import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.favourites.domain.repository.FavouriteRepository
import java.io.IOException
import javax.inject.Inject

class GetFavouriteMoviesUseCase @Inject constructor(private val favouriteRepository: FavouriteRepository) {
    suspend fun getFavouriteMovies(): UseCaseResult<List<FavouriteMovie>> {
        return try {
            UseCaseResult.Success(favouriteRepository.getAllFavouriteMovies())
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}