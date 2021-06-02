package com.adrian.home.data.network.model.authorreview


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorReview(
    val author: String?,
    @Json(name = "author_details")
    val authorDetails: AuthorDetails?,
    val content: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    val id: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    val url: String?
)