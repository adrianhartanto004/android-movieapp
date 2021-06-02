package com.adrian.home.data.network.model.moviephoto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Backdrop(
    @Json(name = "file_path")
    val filePath: String?
)