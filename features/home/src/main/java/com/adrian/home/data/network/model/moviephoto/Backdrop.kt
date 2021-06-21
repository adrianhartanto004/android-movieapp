package com.adrian.home.data.network.model.moviephoto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Backdrop(
    @SerialName("file_path")
    val filePath: String?
)