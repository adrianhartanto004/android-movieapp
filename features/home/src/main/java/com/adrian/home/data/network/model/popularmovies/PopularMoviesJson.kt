package com.adrian.home.data.network.model.popularmovies

import com.adrian.home.data.database.model.popularmovies.PopularMoviesEntity
import com.adrian.home.domain.model.PopularMovies
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMoviesJson(
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String,
    val id: Int,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

internal fun PopularMoviesJson.toEntity() =
    PopularMoviesEntity(
        adult,
        backdropPath,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )

internal fun PopularMoviesJson.toDomainModel() =
    PopularMovies(
        adult,
        backdropPath,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )