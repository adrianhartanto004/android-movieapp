package com.adrian.home.data.database.model.genre

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adrian.home.domain.model.genre.Genre

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey
    val id: Int,
    val name: String
)

internal fun GenreEntity.toDomainModel() =
    Genre(
        id, name
    )