package com.adrian.home.domain.model.nowplayingmovies

data class NowPlayingMoviesList(
    val page: Int,
    val results: List<NowPlayingMovies>,
    val totalPages: Int,
    val totalResults: Int
)