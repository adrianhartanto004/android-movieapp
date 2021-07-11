package com.adrian.home.domain.usecase

import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    fun getGenres(): Flow<List<Genre>> {
        return homeRepository.getGenres()
    }
}
