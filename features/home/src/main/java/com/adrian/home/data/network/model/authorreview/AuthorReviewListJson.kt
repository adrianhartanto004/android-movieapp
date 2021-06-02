package com.adrian.home.data.network.model.authorreview


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorReviewListJson(
    val id: Int = 0,
    val page: Int = 0,
    @Json(name = "results")
    val authorReviews: List<AuthorReview> = listOf(),
    @Json(name = "total_pages")
    val totalPages: Int = 0,
    @Json(name = "total_results")
    val totalResults: Int = 0
)