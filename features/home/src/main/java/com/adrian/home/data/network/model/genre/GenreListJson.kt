package com.adrian.home.data.network.model.genre

import com.adrian.home.domain.model.genre.GenreList
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreListJson(
    val genres: List<GenreJson>
)

internal fun GenreListJson.toDomainModel() = GenreList(
    genres.map { it.toDomainModel() }
)