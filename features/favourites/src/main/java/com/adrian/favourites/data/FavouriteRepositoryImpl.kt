package com.adrian.favourites.data

import com.adrian.abstraction.common.data.SharedDao
import com.adrian.abstraction.common.data.model.toDomainModel
import com.adrian.abstraction.common.domain.model.FavouriteMovie
import com.adrian.favourites.domain.repository.FavouriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class FavouriteRepositoryImpl  (private val sharedDao: SharedDao) :
    FavouriteRepository {
    override suspend fun getAllFavouriteMovies(): List<FavouriteMovie> {
        return try {
            withContext(Dispatchers.IO) {
                sharedDao.getAllFavouriteMovies().map { it.toDomainModel() }
            }
        } catch (e: IOException) {
            sharedDao.getAllFavouriteMovies().map { it.toDomainModel() }
        }
    }
}