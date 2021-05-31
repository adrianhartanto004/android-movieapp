package com.adrian.home.data.network.model.genre

import com.adrian.home.data.database.model.genre.GenreEntity
import com.adrian.home.domain.model.genre.Genre
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreJson(
    val id: Int,
    val name: String
)

internal fun GenreJson.toEntity() =
    GenreEntity(
        id, name
    )

internal fun GenreJson.toDomainModel() =
    Genre(
        id, name
    )