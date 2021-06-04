package com.adrian.favourites.domain.repository

import com.adrian.abstraction.common.domain.model.FavouriteMovie

interface FavouriteRepository {
    suspend fun getAllFavouriteMovies(): List<FavouriteMovie>
}