package com.adrian.home.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adrian.home.data.database.model.genre.GenreEntity
import com.adrian.home.data.database.model.nowplayingmovies.NowPlayingMoviesEntity
import com.adrian.home.data.database.model.popularmovies.PopularMoviesEntity

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMovies(movies: List<PopularMoviesEntity>)

    @Query("SELECT * FROM popularMovies")
    suspend fun getAllPopularMovies(): List<PopularMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNowPlayingMovies(movies: List<NowPlayingMoviesEntity>)

    @Query("SELECT * FROM nowPlayingMovies")
    suspend fun getAllNowPlayingMovies(): List<NowPlayingMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenres(genres: List<GenreEntity>)

    @Query("SELECT * FROM genre")
    suspend fun getAllGenres(): List<GenreEntity>
}
