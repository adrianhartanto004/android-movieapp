package com.adrian.home.data.database.model.nowplayingmovies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList

@Entity(tableName = "nowPlayingMovies")
data class NowPlayingMoviesEntity(
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

internal fun NowPlayingMoviesEntity.toDomainModel() =
    NowPlayingMovies(
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

internal fun List<NowPlayingMoviesEntity>.toDomainModel() =
    NowPlayingMoviesList(
        1,
        this.map { it.toDomainModel() },
        1,
        this.size
    )