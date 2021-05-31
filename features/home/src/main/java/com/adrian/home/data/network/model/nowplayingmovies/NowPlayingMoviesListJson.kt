package com.adrian.home.data.network.model.nowplayingmovies

import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NowPlayingMoviesListJson(
    val page: Int,
    @Json(name = "results")
    val results: List<NowPlayingMoviesJson>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int
)

internal fun NowPlayingMoviesListJson.toDomainModel() = NowPlayingMoviesList(
    page, results.map { it.toDomainModel() }, totalPages, totalResults
)