package dev.nmgalo.core.model.messenger

data class Message(
    val id: Long,
    val chatId: Long,
    val message: String,
    val senderId: Long,
    val status: MessageStatus,
    val createdAt: Long,
)
