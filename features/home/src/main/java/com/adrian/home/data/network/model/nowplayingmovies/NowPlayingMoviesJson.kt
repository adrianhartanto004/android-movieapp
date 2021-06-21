package com.adrian.home.data.network.model.nowplayingmovies

import com.adrian.home.data.database.model.nowplayingmovies.NowPlayingMoviesEntity
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingMoviesJson(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
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

internal fun NowPlayingMoviesJson.toEntity() =
    NowPlayingMoviesEntity(
        adult,
        backdropPath,
        genreIds,
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

internal fun NowPlayingMoviesJson.toDomainModel() =
    NowPlayingMovies(
        adult,
        backdropPath,
        genreIds,
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