package com.adrian.home.domain.usecase

import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    fun getNowPlayingMovies(page: Int): Flow<NowPlayingMoviesList> {
        return homeRepository.getNowPlayingMovies(page)
    }
}
