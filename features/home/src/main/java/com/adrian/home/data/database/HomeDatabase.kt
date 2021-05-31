package com.adrian.home.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrian.home.data.database.model.nowplayingmovies.NowPlayingMoviesEntity
import com.adrian.home.data.database.model.popularmovies.PopularMoviesEntity

@Database(
    entities = [PopularMoviesEntity::class,
        NowPlayingMoviesEntity::class], version = 1, exportSchema = false
)
abstract class HomeDatabase : RoomDatabase() {

    abstract fun homeDao(): HomeDao

    companion object {
        const val DATABASE_NAME = "Home.db"
    }
}