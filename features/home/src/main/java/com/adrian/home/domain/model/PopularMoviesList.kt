package com.adrian.home.domain.model

data class PopularMoviesList(
    val page: Int,
    val results: List<PopularMovies>,
    val totalPages: Int,
    val totalResults: Int
)