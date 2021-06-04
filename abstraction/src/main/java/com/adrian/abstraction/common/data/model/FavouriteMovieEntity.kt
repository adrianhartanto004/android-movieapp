package com.adrian.abstraction.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adrian.abstraction.common.domain.model.FavouriteMovie

@Entity(tableName = "favouriteMovies")
data class FavouriteMovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
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

fun FavouriteMovieEntity.toDomainModel() =
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

fun FavouriteMovie.toEntity() =
    FavouriteMovieEntity(
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