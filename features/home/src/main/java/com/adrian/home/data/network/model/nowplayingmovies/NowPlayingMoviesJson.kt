package com.adrian.home.data.network.model.nowplayingmovies

import com.adrian.home.data.database.model.nowplayingmovies.NowPlayingMoviesEntity
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NowPlayingMoviesJson(
    val adult: Boolean,
//    @Json(name = "backdrop_path")
//    val backdropPath: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
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

internal fun NowPlayingMoviesJson.toEntity() =
    NowPlayingMoviesEntity(
        adult,
//        backdropPath,
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
//        backdropPath,
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
