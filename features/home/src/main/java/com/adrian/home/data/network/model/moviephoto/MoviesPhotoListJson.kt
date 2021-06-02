package com.adrian.home.data.network.model.moviephoto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesPhotoListJson(
    val id: Int?,
    val backdrops: List<Backdrop>?
)