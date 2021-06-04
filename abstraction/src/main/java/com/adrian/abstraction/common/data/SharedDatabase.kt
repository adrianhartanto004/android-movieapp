package com.adrian.abstraction.common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adrian.abstraction.common.data.model.FavouriteMovieEntity

@Database(
    entities = [FavouriteMovieEntity::class], version = 1, exportSchema = false
)
abstract class SharedDatabase : RoomDatabase() {

    abstract fun sharedDao(): SharedDao

    companion object {
        const val DATABASE_NAME = "Shared.db"
    }
}