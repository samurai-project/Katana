package dev.nmgalo.core.model.chat

data class Chat(
    val id: Long,
    val lastUserId: Long,
    val lastMessageId: Long
)
