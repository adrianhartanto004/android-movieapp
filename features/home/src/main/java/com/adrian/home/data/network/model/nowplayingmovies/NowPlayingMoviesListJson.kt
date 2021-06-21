package com.adrian.home.data.network.model.nowplayingmovies

import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesListJson(
    val page: Int,
    @SerialName("results")
    val results: List<NowPlayingMoviesJson>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)

internal fun NowPlayingMoviesListJson.toDomainModel() = NowPlayingMoviesList(
    page, results.map { it.toDomainModel() }, totalPages, totalResults
)