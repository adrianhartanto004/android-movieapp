package com.adrian.home.data.network.model.genre

import com.adrian.home.domain.model.genre.GenreList
import kotlinx.serialization.Serializable

@Serializable
data class GenreListJson(
    val genres: List<GenreJson>
)

internal fun GenreListJson.toDomainModel() = GenreList(
    genres.map { it.toDomainModel() }
)