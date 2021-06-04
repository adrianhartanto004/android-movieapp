package com.adrian.abstraction.common.data

import androidx.room.*
import com.adrian.abstraction.common.data.model.FavouriteMovieEntity

@Dao
interface SharedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieToFavourite(favouriteMovieEntity: FavouriteMovieEntity)

    @Query("SELECT * FROM favouriteMovies")
    suspend fun getAllFavouriteMovies(): List<FavouriteMovieEntity>

    @Delete
    suspend fun deleteFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM favouriteMovies WHERE id = :id)")
    suspend fun isFavouriteMovieExist(id: Int): Boolean
}