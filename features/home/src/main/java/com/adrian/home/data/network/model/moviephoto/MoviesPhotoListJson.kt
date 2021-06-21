package com.adrian.home.data.network.model.moviephoto

import kotlinx.serialization.Serializable

@Serializable
data class MoviesPhotoListJson(
    val id: Int?,
    val backdrops: List<Backdrop>?
)