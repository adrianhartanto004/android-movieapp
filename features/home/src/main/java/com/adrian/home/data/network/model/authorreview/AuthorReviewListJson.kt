package com.adrian.home.data.network.model.authorreview


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorReviewListJson(
    val id: Int = 0,
    val page: Int = 0,
    @SerialName("results")
    val authorReviews: List<AuthorReview> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)