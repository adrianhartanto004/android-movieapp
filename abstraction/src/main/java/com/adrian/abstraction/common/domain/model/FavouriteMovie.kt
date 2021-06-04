package com.adrian.abstraction.common.domain.model

data class FavouriteMovie(
    val id: Int,
    val originalLanguage: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)