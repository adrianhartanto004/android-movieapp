package com.adrian.movieapp.data.network.model


//import com.squareup.moshi.Json
//import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class MovieListResponse(
//    val dates: Dates?,
//    val page: Int?,
    val results: List<Result>?,
//    @Json(name = "total_pages")
//    val totalPages: Int?,
//    @Json(name = "total_results")
//    val totalResults: Int?
)