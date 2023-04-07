package dev.nmgalo.core.network.messenger.model

@kotlinx.serialization.Serializable
data class ChatDTO(
    val id: Long,
    val lastUserId: Long,
    val lastMessageId: Long?
)
