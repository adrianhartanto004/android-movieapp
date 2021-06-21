package com.adrian.home.data.network.model.moviecredits

import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditListJson(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)