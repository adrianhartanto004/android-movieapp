package com.adrian.home.data.network.model.authorreview


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetails(
    @Json(name = "avatar_path")
    val avatarPath: String?,
    val rating: Double?
)