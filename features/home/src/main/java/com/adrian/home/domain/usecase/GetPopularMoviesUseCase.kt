package com.adrian.home.domain.usecase

import com.adrian.home.domain.model.popularmovies.PopularMoviesList
import com.adrian.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    fun getPopularMovies(page: Int): Flow<PopularMoviesList> {
        return homeRepository.getPopularMovies(page)
    }
}
