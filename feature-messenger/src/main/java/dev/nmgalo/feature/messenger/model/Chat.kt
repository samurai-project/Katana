package dev.nmgalo.feature.messenger.model

data class Chat(
    val id: Long,
    val lastUserId: Long,
    val lastMessageId: Long
)
