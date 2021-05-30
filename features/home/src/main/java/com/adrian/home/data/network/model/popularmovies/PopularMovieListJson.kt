package com.adrian.home.data.network.model.popularmovies

import com.adrian.home.domain.model.PopularMoviesList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMovieListJson(
    val page: Int,
    @Json(name = "results")
    val results: List<PopularMoviesJson>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

internal fun PopularMovieListJson.toDomainModel() = PopularMoviesList(
    page, results.map { it.toDomainModel() }, totalPages, totalResults
)