package dev.nmgalo.core.network.messenger.model

@kotlinx.serialization.Serializable
data class MessageDTO(
    val id: Long,
    val chatId: Long,
    val message: String,
    val senderId: Long,
    val status: Int,
    val createdAt: Long,
)
