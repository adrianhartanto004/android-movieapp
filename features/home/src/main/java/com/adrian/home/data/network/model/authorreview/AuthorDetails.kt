package com.adrian.home.data.network.model.authorreview


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorDetails(
    @SerialName("avatar_path")
    val avatarPath: String?,
    val rating: Double?
)