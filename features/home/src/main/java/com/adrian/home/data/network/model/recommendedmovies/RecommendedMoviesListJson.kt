package com.adrian.home.data.network.model.recommendedmovies

import com.adrian.home.domain.model.recommendedmovies.RecommendedMoviesList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecommendedMoviesListJson(
    val page: Int,
    @Json(name = "results")
    val results: List<RecommendedMoviesJson>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

internal fun RecommendedMoviesListJson.toDomainModel() = RecommendedMoviesList(
    page, results.map { it.toDomainModel() }, totalPages, totalResults
)