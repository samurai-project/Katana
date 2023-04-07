package dev.nmgalo.core.network.fake.model

@kotlinx.serialization.Serializable
data class ChatDTO(
    val id: Long,
    val lastUserId: Long,
    val lastMessageId: Long?
)
