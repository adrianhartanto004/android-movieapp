package com.adrian.home.data.database.model.nowplayingmovies

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList

@Entity(tableName = "nowPlayingMovies")
@TypeConverters(IntConverter::class)
data class NowPlayingMoviesEntity(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int> = listOf(),
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
        genreIds,
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

class IntConverter {
    @TypeConverter
    fun gettingListFromString(genreIds: String): List<Int> {
        val list = mutableListOf<Int>()

        val array = genreIds.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }

    @TypeConverter
    fun writingStringFromList(list: List<Int>): String {
        var genreIds = ""
        for (i in list) genreIds += ",$i"
        return genreIds
    }
}