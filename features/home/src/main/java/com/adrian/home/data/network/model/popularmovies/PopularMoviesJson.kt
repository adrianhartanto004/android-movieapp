package com.adrian.home.data.network.model.popularmovies

import com.adrian.home.data.database.model.popularmovies.PopularMoviesEntity
import com.adrian.home.domain.model.popularmovies.PopularMovies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMoviesJson(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    val id: Int,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
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