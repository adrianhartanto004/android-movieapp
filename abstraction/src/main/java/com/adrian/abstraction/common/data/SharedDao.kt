package com.adrian.abstraction.common.data

import androidx.room.*
import com.adrian.abstraction.common.data.model.FavouriteMovieEntity

@Dao
interface SharedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieToFavourite(favouriteMovieEntity: FavouriteMovieEntity)

    @Query("SELECT * FROM favouriteMovies")
    fun getAllFavouriteMovies(): List<FavouriteMovieEntity>

    @Delete
    fun deleteFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM favouriteMovies WHERE id = :id)")
    fun isFavouriteMovieExist(id: Int): Boolean
}
