package com.adrian.home.data.network.model.recommendedmovies

import com.adrian.home.domain.model.recommendedmovies.RecommendedMoviesList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendedMoviesListJson(
    val page: Int,
    @SerialName("results")
    val results: List<RecommendedMoviesJson>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

internal fun RecommendedMoviesListJson.toDomainModel() = RecommendedMoviesList(
    page, results.map { it.toDomainModel() }, totalPages, totalResults
)