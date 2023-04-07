package dev.nmgalo.core.network.messenger.model

import kotlinx.serialization.Serializable

@Serializable
data class SendMessageDTO(
    val chatId: Long,
    val message: String,
    val createdAt: Long,
)
