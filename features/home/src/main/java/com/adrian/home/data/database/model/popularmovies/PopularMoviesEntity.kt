package com.adrian.home.data.database.model.popularmovies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adrian.home.domain.model.PopularMovies
import com.adrian.home.domain.model.PopularMoviesList

@Entity(tableName = "popularMovies")
data class PopularMoviesEntity(
    val adult: Boolean,
    val backdropPath: String,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

internal fun PopularMoviesEntity.toDomainModel() =
    PopularMovies(
        adult,
        backdropPath,
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

internal fun List<PopularMoviesEntity>.toDomainModel() =
    PopularMoviesList(
        1,
        this.map { it.toDomainModel() },
        1,
        this.size
    )