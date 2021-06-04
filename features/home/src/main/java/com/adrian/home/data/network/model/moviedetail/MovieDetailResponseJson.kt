package com.adrian.home.data.network.model.moviedetail

import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.home.data.network.model.genre.GenreJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponseJson(
    val budget: Int? = null,
    val genres: List<GenreJson>? = null,
    val homepage: String? = null,
    val id: Int = 0,
    @Json(name = "imdb_id")
    val imdbId: String? = null,
    @Json(name = "original_language")
    val originalLanguage: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @Json(name = "vote_average")
    val voteAverage: Double? = null,
    @Json(name = "vote_count")
    val voteCount: Int? = null
)

internal fun MovieDetailResponseJson.toFavouriteMovie() =
    FavouriteMovie(
        id,
        originalLanguage,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )