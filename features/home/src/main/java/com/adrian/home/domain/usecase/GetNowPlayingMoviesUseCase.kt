package com.adrian.home.domain.usecase

import com.adrian.abstraction.common.state.UseCaseResult
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun getNowPlayingMovies(page: Int): UseCaseResult<NowPlayingMoviesList> {
        return try {
            UseCaseResult.Success(homeRepository.getNowPlayingMovies(page))
        } catch (e: IOException) {
            UseCaseResult.Error(e)
        }
    }
}