package com.adrian.home.domain.model.recommendedmovies


data class RecommendedMoviesList(
    val page: Int,
    val results: List<RecommendedMovies>,
    val totalPages: Int,
    val totalResults: Int
)