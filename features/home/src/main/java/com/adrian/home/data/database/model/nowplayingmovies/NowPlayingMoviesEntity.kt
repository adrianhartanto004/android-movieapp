package com.adrian.home.data.database.model.nowplayingmovies

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMovies
import com.adrian.home.domain.model.nowplayingmovies.NowPlayingMoviesList
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Entity(tableName = "nowPlayingMovies")
@TypeConverters(IntConverter::class)
data class NowPlayingMoviesEntity(
    val adult: Boolean,
//    val backdropPath: String,
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
//        backdropPath,
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

internal class IntConverter {
    private val type = Types.newParameterizedType(List::class.java, Integer::class.java)
    private val adapter: JsonAdapter<List<Int>> = Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun intToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToInt(someObjects: List<Int>): String? =
        adapter.toJson(someObjects)
}
