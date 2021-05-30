package com.adrian.home.domain.repository

import com.adrian.home.domain.model.PopularMoviesList

interface HomeRepository {
    suspend fun getPopularMovies(page: Int): PopularMoviesList
}