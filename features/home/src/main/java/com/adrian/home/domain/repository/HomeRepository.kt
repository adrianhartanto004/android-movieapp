package com.adrian.home.domain.repository

import com.adrian.home.domain.model.genre.Genre
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.adrian.home.domain.model.popularmovies.PopularMoviesList

interface HomeRepository {
    suspend fun getPopularMovies(page: Int): PopularMoviesList
    suspend fun getNowPlayingMovies(page: Int): NowPlayingMoviesList
    suspend fun getGenres(): List<Genre>
}