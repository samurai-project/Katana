package dev.nmgalo.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class WallDTO(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
)
