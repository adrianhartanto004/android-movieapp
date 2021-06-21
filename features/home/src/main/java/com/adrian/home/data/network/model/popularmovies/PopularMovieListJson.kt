package com.adrian.home.data.network.model.popularmovies

import com.adrian.home.domain.model.popularmovies.PopularMoviesList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMovieListJson(
    val page: Int,
    @SerialName("results")
    val results: List<PopularMoviesJson>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

internal fun PopularMovieListJson.toDomainModel() = PopularMoviesList(
    page, results.map { it.toDomainModel() }, totalPages, totalResults
)