package com.adrian.home.data.network.model.moviedetail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    val id: Int?,
    val name: String?,
    @Json(name = "origin_country")
    val originCountry: String?
)