package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.data.network.model.moviephoto.MoviesPhotoListJson
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class GetMoviePhotosUseCase  (private val homeRepository: HomeRepository) {
    suspend fun getMoviePhotos(movieId: Int): UseCaseResult<MoviesPhotoListJson> {
        return try {
            UseCaseResult.Success(homeRepository.getMoviePhotos(movieId))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}