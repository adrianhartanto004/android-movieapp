package com.adrian.home.data.network.model.moviedetail

import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.home.data.network.model.genre.GenreJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailResponseJson(
    val budget: Int? = null,
    val genres: List<GenreJson>? = null,
    val homepage: String? = null,
    val id: Int = 0,
    @SerialName("imdb_id")
    val imdbId: String? = null,
    @SerialName("original_language")
    val originalLanguage: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,
    val title: String? = null,
    val video: Boolean? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("vote_count")
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