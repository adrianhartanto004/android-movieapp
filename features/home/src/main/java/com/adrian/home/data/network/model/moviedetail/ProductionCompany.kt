package com.adrian.home.data.network.model.moviedetail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompany(
    val id: Int?,
    val name: String?,
    @SerialName("origin_country")
    val originCountry: String?
)