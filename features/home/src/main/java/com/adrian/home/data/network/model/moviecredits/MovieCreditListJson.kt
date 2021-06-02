package com.adrian.home.data.network.model.moviecredits

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCreditListJson(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)