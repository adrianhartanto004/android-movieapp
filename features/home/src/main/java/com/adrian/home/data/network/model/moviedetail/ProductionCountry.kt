package com.adrian.home.data.network.model.moviedetail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    @Json(name = "iso_3166_1")
    val iso31661: String?,
    val name: String?
)