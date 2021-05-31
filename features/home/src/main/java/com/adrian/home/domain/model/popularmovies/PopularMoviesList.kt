package com.adrian.home.domain.model.popularmovies

data class PopularMoviesList(
    val page: Int,
    val results: List<PopularMovies>,
    val totalPages: Int,
    val totalResults: Int
)